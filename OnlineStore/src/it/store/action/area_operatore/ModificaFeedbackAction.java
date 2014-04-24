package it.store.action.area_operatore;

import java.sql.SQLException;

import it.store.dto.User;
import it.store.service.FeedbackService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ModificaFeedbackAction extends ActionSupport implements Preparable {
	
	private static final int APPROVA = 1;
	private static final int RIFIUTA = 0;
	

	private User userData;
	
	public int id_ordine = -1;
	
	@Override
	public void prepare() {
		ActionContext context = ActionContext.getContext();
		userData = (User)context.getSession().get("userData");
	}
	
	public void validate() {
		if(id_ordine<0) {
			addActionError("errori.generico");
		}
	}
	
	public String accetta() {
		
		if(userData==null) { //??
			return LOGIN;
		}
		
		FeedbackService feedbackService;
		try {
			feedbackService = new FeedbackService();
			feedbackService.modificaFeedback(APPROVA, userData.email, id_ordine);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} finally {
			feedbackService = null;
		}
		
		return SUCCESS;
	}
	
	public String rifiuta() {
		
		if(userData==null) { //??
			return LOGIN;
		}
		
		FeedbackService feedbackService;
		try {
			feedbackService = new FeedbackService();
			feedbackService.modificaFeedback(RIFIUTA, userData.email, id_ordine);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} finally {
			feedbackService = null;
		}
		
		return SUCCESS;
	}
	
}
