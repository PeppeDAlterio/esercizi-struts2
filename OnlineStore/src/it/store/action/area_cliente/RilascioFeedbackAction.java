package it.store.action.area_cliente;

import java.sql.SQLException;

import it.store.dto.Feedback;
import it.store.dto.User;
import it.store.service.FeedbackService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RilascioFeedbackAction extends ActionSupport implements ModelDriven<Feedback> {

	private Feedback feedback = new Feedback();
	
	public void validate() {
		if(feedback.getPunteggio()>5 || feedback.getPunteggio()<1) {
			addFieldError("punteggio", getText("fieldError.formato_errato"));
		}
	}
	
	public String execute() {
		
		if(feedback.getId_ordine()==-1) { //ordine non trovato
			addActionError("errore.ordine.non_trovato");
			return ERROR;
		}
		
		ActionContext context = ActionContext.getContext();
		User userData = (User)context.getSession().get("userData");
		
		if(userData==null) { //??
			return LOGIN;
		}
		
		boolean errore = false; //false=ok | true=errore
		
		FeedbackService feedbackService;
		try {
			feedbackService = new FeedbackService();
			errore = feedbackService.inserisciFeedback(feedback, userData.email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(getText("sql.generic"));
		} finally {
			feedbackService = null;
			
			if(hasErrors()) {
				return INPUT;
			}
		}
		
		if(errore) {
			addActionError(getText("errore.ordine.non_trovato"));
			return ERROR;
		}
		
		addActionMessage(getText("feedback.success"));
		return SUCCESS;
	}

	@Override
	public Feedback getModel() {
		return feedback;
	}
	
}
