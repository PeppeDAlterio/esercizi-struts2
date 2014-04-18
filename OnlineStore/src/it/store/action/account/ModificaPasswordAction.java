package it.store.action.account;

import it.store.service.AccountService;
import it.store.utils.MD5Encoder;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * Action che richiede, previa recezione dei dati da request, la modifica della password per l'account (email passata sempre tramite request).
 */

@SuppressWarnings("serial")
public class ModificaPasswordAction extends ActionSupport {

	public String email;
	public String vecchia_password;
	public List<String> nuova_password;
	
	public void validate() {
		/* controllo che le due password siano uguali e che siano state inserite */
		if(nuova_password!=null) {
			if( !(nuova_password.get(0).equals(nuova_password.get(1))) ) {
				addFieldError("nuova_password", "Le due password non combaciano");
			}
		} else {
			addFieldError("nuova_password", "Nuova password obbligatoria");
		}
		
		if(StringUtils.isBlank(vecchia_password)) {
			addFieldError("vecchia_password", "Campo obbligatorio");
		}
		
		if(StringUtils.isBlank(email)) {
			addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");
		}
	}
		
	public String execute() {
		
		String nuova_password = this.nuova_password.get(0);
		
		/* se la nuova password è uguale alla vecchia, annullo la modifica */
		if(nuova_password.equals(vecchia_password)) {
			addActionError("La nuova password uguale alla password attualmente in uso.");
			return INPUT;
		}
		
		MD5Encoder md5 = new MD5Encoder();
		vecchia_password 	= md5.encodeString(vecchia_password);
		nuova_password		= md5.encodeString(nuova_password);
		
		
		AccountService accountService;
		try {
			accountService = new AccountService();
			if(accountService.modificaPassword(email, vecchia_password, nuova_password) == 0) {
				addActionError("Si e' verificato un errore:<br>"
								+ "Password errata!");
				addFieldError("vecchia_password", "Password errata");
			}
		} catch (ClassNotFoundException|SQLException e) {
			addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");
		} finally {
			accountService = null;
			
			if(hasActionErrors()) {
				return ERROR;
			}
		}
		
		/* se tutto è andato a buon fine, richiedo il login con le nuove credenziali */
		/* pulisco la session (logout) e riporto l'utente alla pagina di login */
		ActionContext context = ActionContext.getContext();
		context.getSession().remove("userData");
		
		addActionMessage("Password modificata con successo.<br>"
						+ "Effettua di nuovo l'accesso con le nuove credenziali.");
		
		return SUCCESS;
	}
	
}
