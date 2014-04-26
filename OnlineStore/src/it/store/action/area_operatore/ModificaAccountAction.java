package it.store.action.area_operatore;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.User;
import it.store.service.AccountService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ModificaAccountAction extends ActionSupport implements ModelDriven<User>, Preparable {
	
	public String edit = "true";
	
	private String old_tipo;
	
	private User nuoviDati = new User();
	
	private User userData; //account in uso
	
	@Override
	public void prepare() {
		ActionContext context = ActionContext.getContext();
		userData = (User)context.getSession().get("userData");
	}
	
	
	public void validate() {
		if(StringUtils.isBlank(userData.userId)) {
			addFieldError("userId", getText("fieldError.campo_vuoto"));
		}

		if(StringUtils.isBlank(userData.nome)) {
			addFieldError("nome", getText("fieldError.campo_vuoto"));
		}
		if(StringUtils.isBlank(userData.cognome)) {
			addFieldError("cognome", getText("fieldError.campo_vuoto"));
		}
		if(StringUtils.isBlank(userData.getCodice_fiscale())) {
			addFieldError("codice_fiscale", getText("fieldError.campo_vuoto"));
		} else if(userData.getCodice_fiscale().length()!=16) {
			addFieldError("codice_fiscale", getText("fieldError.formato_errato"));
		}
		
		if(hasFieldErrors()) {
			return;
		}

		/* controllo validit� email primaria ed eventuale email secondaria */
		if( !(userData.email.indexOf("@")>0 && userData.email.lastIndexOf(".")-userData.email.indexOf("@")>1) ) {
			addFieldError("email", getText("fieldError.formato_errato"));
		}
		if(!StringUtils.isBlank(userData.email_secondaria)) {
			if( !(userData.email_secondaria.indexOf("@")>0 && 
					userData.email_secondaria.lastIndexOf(".")-userData.email_secondaria.indexOf("@")>1 ) ) {
				addFieldError("email_secondaria", getText("fieldError.formato_errato"));
			}
		} else {
			userData.email_secondaria = null;
		}
		
		/* controllo la validit� dei numeri di telefono */
		if(!StringUtils.isBlank(userData.telefono_fisso)) {
			if( !(StringUtils.isNumeric(userData.telefono_fisso) && userData.telefono_fisso.length()>9) ) {
				addFieldError("telefono_fisso", getText("fieldError.formato_errato"));
			}
		} else {
			userData.telefono_fisso = null;
		}
		if(!StringUtils.isBlank(userData.telefono_mobile)) {
			if( !(StringUtils.isNumeric(userData.telefono_mobile) && userData.telefono_mobile.length()>9) ) {
				addFieldError("telefono_mobile", getText("fieldError.formato_errato"));
			}
		} else {
			userData.telefono_mobile = null;
		}
	}
	
	
	public String execute() {
		
		int tipo_account = -1;
		try {
			tipo_account = Integer.parseInt(getOld_tipo());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
			return ERROR;
		} finally {
			if(tipo_account<0) {
				addActionError(getText("errori.generico"));
				return ERROR;
			}
		}
		
		if(userData==null) { //??
			return LOGIN;
		}
		
		//controllo che l'account modificato sia di tipo uguale o inferiore
		//il controllo che sia operatore viene effettuato dall'interceptor
		if(userData.getTipo()<tipo_account) {
			return "access_denied";
		}
		
		AccountService accountService;
		try {
			accountService = new AccountService();
			accountService.modificaAccount(nuoviDati.email, nuoviDati);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			/* messaggi di errore per 'duplicate' (email gi� in uso, userId gi� in uso,...) */
			controllo_duplicati(e.getMessage());
		} finally {
			accountService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		edit = null; //fine modifica
		
		return SUCCESS;
	}
	
	private void controllo_duplicati(String errore) {
		if(errore.contains("Duplicate")) {
			if(errore.contains("PRIMARY")) {
				addActionError(getText("errori.user.email_in_uso"));
				addFieldError("email", getText("errori.user.email_in_uso"));
			}
			else if (errore.contains("userid")) {
				addActionError(getText("errori.user.userid_in_uso"));
				addFieldError("userId", getText("errori.user.userid_in_uso"));
			}
			else if (errore.contains("codice_fiscale")) {
				addActionError(getText("errori.user.cf_in_uso"));
				addFieldError("codice_fiscale", getText("errori.user.cf_in_uso"));
			}
		} else {
			addActionError(getText("sql.generic"));
		}
	}

	public String getOld_tipo() {
		return old_tipo;
	}


	public void setOld_tipo(String old_tipo) {
		this.old_tipo = old_tipo;
	}


	public User getNuoviDati() {
		return nuoviDati;
	}


	public void setNuoviDati(User nuoviDati) {
		this.nuoviDati = nuoviDati;
	}


	public User getUserData() {
		return userData;
	}


	public void setUserData(User userData) {
		this.userData = userData;
	}


	@Override
	public User getModel() {
		return nuoviDati;
	}

}
