package it.store.action.area_cliente;

import it.store.dto.Feedback;
import it.store.dto.User;
import it.store.service.FeedbackService;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GestioneFeedbackAction extends ActionSupport {
	
	private List<Feedback> listaFeedback;
	
	public String execute() {
		
		ActionContext context = ActionContext.getContext();
		User userData = (User)context.getSession().get("userData");
		
		if(userData==null) { // ??
			return LOGIN;
		}
		
		
		FeedbackService feedbackService;
		try {
			feedbackService = new FeedbackService();
			setListaFeedback(feedbackService.getListaFbUtente(userData.email));
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} finally {
			feedbackService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
				
		return SUCCESS;
	}

	public List<Feedback> getListaFeedback() {
		return listaFeedback;
	}

	public void setListaFeedback(List<Feedback> listaFeedback) {
		this.listaFeedback = listaFeedback;
	}

}
