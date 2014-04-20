package it.store.action.area_admin;

import it.store.dto.User;
import it.store.service.AccountService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CreaAccountAction extends ActionSupport implements ModelDriven<User> {
	
	private User userData = new User();

	public List<String> psw = null;
	
	public void validate() {
		/* controllo che le due password siano uguali e che siano state inserite */
		if (psw!=null) {
			if(psw.get(0).equals(psw.get(1))) {
				userData.setPassword(psw.get(0));
			} else {
				addFieldError("psw", getText("errori.user.psw.diverse"));
			}
		}
		
		/* controllo che non vi siano campi vuoti */
		if(StringUtils.isBlank(userData.email)) {
			addFieldError("email", getText("fieldError.campo_vuoto"));
		}
		if(StringUtils.isBlank(userData.userId)) {
			addFieldError("userId", getText("fieldError.campo_vuoto"));
		}
		if(StringUtils.isBlank(userData.getPassword())) {
			addFieldError("password", getText("fieldError.campo_vuoto"));
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

		/* controllo validità email primaria ed eventuale email secondaria */
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
		
		/* controllo la validità dei numeri di telefono */
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
		
		if(userData.getTipo()<0 || userData.getTipo()>3) {
			addFieldError("tipo", getText("fieldError.formato_errato"));
		}
	}
	
	public String execute() {
		
		AccountService accountService;
		try {
			accountService = new AccountService();
			accountService.nuovoAccount(userData);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			controllo_duplicati(e.getMessage());
		} finally {
			accountService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		addActionMessage("creaAccount.success");
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

	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

	@Override
	public User getModel() {
		return userData;
	}
	
}
