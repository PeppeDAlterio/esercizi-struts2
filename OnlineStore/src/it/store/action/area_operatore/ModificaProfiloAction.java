package it.store.action.area_operatore;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ModificaProfiloAction extends ActionSupport implements ModelDriven<User>, Preparable {
	//TODO: SDDHGDG
	private String old_email,
				   old_tipo;
	
	private User nuoviDati = new User();
	
	private User userData; //account in uso
	
	@Override
	public void prepare() {
		ActionContext context = ActionContext.getContext();
		userData = (User)context.getSession().get("userData");
	}
	
	
	public void validate() {
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
	}
	
	
	public String execute() {
		return SUCCESS;
	}

	public String getOld_email() {
		return old_email;
	}


	public void setOld_email(String old_email) {
		this.old_email = old_email;
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
