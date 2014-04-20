package it.store.action.area_admin;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreaAccountFormAction extends ActionSupport {
	
	private Map<Integer, String> tipiAccount = new HashMap<Integer, String>();
	
	public String execute() {
		
		tipiAccount.put(1, getText("Cliente"));
		tipiAccount.put(2, getText("Operatore"));
		tipiAccount.put(3, getText("Amministratore"));
		
		return SUCCESS;
	}

	public Map<Integer, String> getTipiAccount() {
		return tipiAccount;
	}

	public void setTipiAccount(Map<Integer, String> tipiAccount) {
		this.tipiAccount = tipiAccount;
	}

}
