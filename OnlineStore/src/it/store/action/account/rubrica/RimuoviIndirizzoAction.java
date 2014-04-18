package it.store.action.account.rubrica;

import java.sql.SQLException;

import it.store.dto.User;
import it.store.service.RubricaService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*
 * Action che richiede la rimozione dell'indirizzo selezionato (tramite id letto da GET request)
 */

@SuppressWarnings("serial")
public class RimuoviIndirizzoAction extends ActionSupport {

	public int id_indirizzo = -1;
	public String email;
	
	public void validate() {
		/* nessun id specificato? */
		if(id_indirizzo==-1) {
			addActionError("Si e' verificato un errore.");
		}
	}
	
	public String execute() {
		ActionContext context = ActionContext.getContext();
		User userData = (User)context.getSession().get("userData");
		
		if(userData==null) return LOGIN;
		
		/* leggo l'email dell'utente loggato */
		email = userData.email;
		
		RubricaService rubricaService;
		try {
			rubricaService = new RubricaService();
			if(rubricaService.rimuoviIndirizzo(id_indirizzo, email) == 0) {
				addActionError("Si e' verificato un errore.");
			}
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");
		} finally {
			rubricaService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		return SUCCESS;
	}
	
}
