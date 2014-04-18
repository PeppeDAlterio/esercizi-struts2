package it.store.action.utente;

import java.sql.SQLException;

import it.store.dto.User;
import it.store.service.AccountService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * Action per richiedere l'accesso al sito con le credenziali in request.
 */

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport implements ModelDriven<User> {
	
	public User userData = new User();
	
	public String logout() {
		/* pulisco la session */
		ActionContext context = ActionContext.getContext();
		context.getSession().remove("userData");
		
		addActionMessage(getText("loginForm.msg.logout_ok"));
		return SUCCESS;
	}
	
	public String login() {
		
		AccountService accountService;
		try {
			accountService = new AccountService();
			if(!accountService.accedi(userData)) {
				addActionError(getText("errori.generico"));
			}
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} finally {
			accountService = null;
			
			if(hasActionErrors()) {
				return ERROR;
			}
		}
		
		/* aggiungo il DTO alla session */
		ActionContext context = ActionContext.getContext();
		context.getSession().put("userData", userData);
		
		addActionMessage(getText("loginForm.msg.login_ok"));
		
		return SUCCESS;
	}

	@Override
	public User getModel() {
		return userData;
	}

}
