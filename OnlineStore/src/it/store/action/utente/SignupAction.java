package it.store.action.utente;

import java.sql.SQLException;
import java.util.List;

import it.store.dto.User;
import it.store.service.AccountService;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * Action che richieda la registrazione di un nuovo account di livello 1 (CLIENTE) con i dati in request.
 */

@SuppressWarnings("serial")
public class SignupAction extends ActionSupport implements ModelDriven<User> {
	
//FIXME: Aggiungi tipo account!
	
	public User userData = new User();
	
	public List<String> psw = null;
			
	public void validate() {
		/* controllo che, in caso di registrazione, le due password siano uguali e che siano state inserite */
		if (psw!=null) {
			if(psw.get(0).equals(psw.get(1))) {
				userData.setPassword(psw.get(0));
			} else {
				addFieldError("psw", "Le password non combaciano");
			}
		}
		
		/* controllo che non vi siano campi vuoti */
		if(StringUtils.isBlank(userData.email)) {
			addFieldError("email", "Campo obbligatorio!");
		}
		if(StringUtils.isBlank(userData.userId)) {
			addFieldError("userId", "Campo obbligatorio!");
		}
		if(StringUtils.isBlank(userData.getPassword())) {
			addFieldError("password", "Campo obbligatorio!");
		}
		if(StringUtils.isBlank(userData.nome)) {
			addFieldError("nome", "Campo obbligatorio!");
		}
		if(StringUtils.isBlank(userData.cognome)) {
			addFieldError("cognome", "Campo obbligatorio!");
		}
		if(StringUtils.isBlank(userData.getCodice_fiscale())) {
			addFieldError("codice_fiscale", "Campo obbligatorio!");
		} else if(userData.getCodice_fiscale().length()!=16) {
			addFieldError("codice_fiscale", "Formato errato");
		}
		
		if(hasFieldErrors()) {
			return;
		}

		/* controllo validità email primaria ed eventuale email secondaria */
		if( !(userData.email.indexOf("@")>0 && userData.email.lastIndexOf(".")-userData.email.indexOf("@")>1) ) {
			addFieldError("email", "Formato email errato!");
		}
		if(!StringUtils.isBlank(userData.email_secondaria)) {
			if( !(userData.email_secondaria.indexOf("@")>0 && 
					userData.email_secondaria.lastIndexOf(".")-userData.email_secondaria.indexOf("@")>1 ) ) {
				addFieldError("email_secondaria", "Formato email errato!");
			}
		} else {
			userData.email_secondaria = null;
		}
		
		/* controllo la validità dei numeri di telefono */
		if(!StringUtils.isBlank(userData.telefono_fisso)) {
			if( !(StringUtils.isNumeric(userData.telefono_fisso) && userData.telefono_fisso.length()>9) ) {
				addFieldError("telefono_fisso", "Formato numero errato!");
			}
		} else {
			userData.telefono_fisso = null;
		}
		if(!StringUtils.isBlank(userData.telefono_mobile)) {
			if( !(StringUtils.isNumeric(userData.telefono_mobile) && userData.telefono_mobile.length()>9) ) {
				addFieldError("telefono_mobile", "Formato numero errato!");
			}
		} else {
			userData.telefono_mobile = null;
		}
		
	}
	
	public String nuovoCliente() {
						
		AccountService accountService;
		try {
			accountService = new AccountService();
			if(accountService.nuovoCliente(userData)!=1) {
				addActionError("Si e' verificato un errore.<br>"
								+ "Riprovare.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getErrorCode()==1644) {
				addActionError("Si e' verificato un errore:<br>"
								+ e.getMessage());
			} else {
				/* messaggi di errore per 'duplicate' (email già in uso, userId già in uso,...) */
				controllo_duplicati(e.getMessage());
			}
		} finally {
			accountService = null;
			if(hasActionErrors()) {
				return ERROR;
			}
		}
		
		addActionMessage("L'account e' stato creato correttamente!<br>"
							+ "Utilizza i tuoi nuovi dati per effettuare l'accesso.");
		return SUCCESS;
	}
	
	public String modificaProfilo() {
		System.out.println("omg");
		AccountService accountService;
		try {
			accountService = new AccountService();
			if(accountService.modificaProfilo(userData)==0) {
				addActionError("Si e' verificato un errore.<br>"
								+ "Riprovare.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getErrorCode()==1644) {
				addActionError("Si e' verificato un errore:<br>"
								+ e.getMessage());
			} else {
				/* messaggi di errore per 'duplicate' (email già in uso, userId già in uso,...) */
				controllo_duplicati(e.getMessage());
			}
		} finally {
			accountService = null;
			
			if(hasActionErrors()) {
				return ERROR;
			}
		}
		
		/* aggiorno il profilo in session */
		ActionContext context = ActionContext.getContext();
		context.getSession().put("userData", userData);
		
		addActionMessage("Profilo modificato con successo.");
		
		return SUCCESS;
	}

	@Override
	public User getModel() {
		return userData;
	}

	private void controllo_duplicati(String errore) {
		if(errore.contains("Duplicate")) {
			if(errore.contains("PRIMARY")) {
				addActionError("E-mail gia' in uso da un altro utente!");
				addFieldError("email", "Email gia' in uso");
			}
			else if (errore.contains("userid")) {
				addActionError("Username gia' in uso da un altro utente!");
				addFieldError("userId", "Username gia' in uso");
			}
			else if (errore.contains("codice_fiscale")) {
				addActionError("Codice fiscale gia' in uso da un altro utente!");
				addFieldError("codice_fiscale", "Codice fiscale gia' in uso");
			}
			else if (errore.contains("email")) {
				addActionError("E-mail secondaria gia' in uso da un altro utente!");
				addFieldError("email_secondaria", "E-mail gia' in uso");
			}
		} else {
			addActionError("Si e' verficato un errore.<br>"
							+ "Ricontrollare i dati inseriti");
		}
	}
	
}
