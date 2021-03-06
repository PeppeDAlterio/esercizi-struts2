package it.store.interceptor;

import com.opensymphony.xwork2.ActionInvocation;

@SuppressWarnings("serial")
public class CheckLoginOperatoreInterceptor extends CheckLoginInterceptor {
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		getUserFromSession();
		
		if(userData!=null) {
			//Login OK
			
			if(userData.getTipo()>1) {
				return arg0.invoke();
			} else {
				//Accesso negato
				System.out.println("Accesso negato (tipo<2)");
				return "access_denied";
			}
		} else {
			//Accesso non effettuato
			System.out.println("Accesso non effettuato");
			return "login";
		}
		
	}

}
