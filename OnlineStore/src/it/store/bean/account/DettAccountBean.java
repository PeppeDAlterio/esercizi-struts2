package it.store.bean.account;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.User;
import it.store.service.AccountService;

public class DettAccountBean {
	
	private String email,
				   userId;
	
	private User userData;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(StringUtils.isBlank(email)) {
			email = null;
		}
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if(StringUtils.isBlank(userId)) {
			userId = null;
		}
		this.userId = userId;
	}

	public User getUserData() {
		
		if(getEmail()!=null) { //utente da email
			
			AccountService accountService;
			try {
				accountService = new AccountService();
				userData = accountService.getAccountByEmail(getEmail());
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
			} finally {
				accountService = null;
			}
			
		} else if (getUserId()!=null) { //utente da userId
			AccountService accountService;
			try {
				accountService = new AccountService();
				userData = accountService.getAccountByUserId(getUserId());
			} catch (ClassNotFoundException|SQLException e) {
				e.printStackTrace();
			} finally {
				accountService = null;
			}
		} else { // null
			userData = new User();
		}
		
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

}
