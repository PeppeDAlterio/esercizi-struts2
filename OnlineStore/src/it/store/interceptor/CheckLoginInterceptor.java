package it.store.interceptor;

import it.store.dto.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class CheckLoginInterceptor implements Interceptor {
	
	protected User userData = null;
	
	protected void getUserFromSession() {
		ActionContext context = ActionContext.getContext();
		this.userData = (User)context.getSession().get("userData");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		getUserFromSession();
		
		if(userData!=null) {
			//Login OK
			return arg0.invoke();
		} else {
			//Accesso non effettuato
			System.out.println("Accesso non effettuato");
			return "login";
		}
		
	}

}
