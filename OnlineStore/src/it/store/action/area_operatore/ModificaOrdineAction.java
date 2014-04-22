package it.store.action.area_operatore;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.Ordine;
import it.store.dto.User;
import it.store.service.OrdineService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ModificaOrdineAction extends ActionSupport implements ModelDriven<Ordine>, Preparable {
	
	private Ordine ordine = new Ordine();
	
	private User userData;
	
	@Override
	public void prepare() throws Exception {
		ActionContext context = ActionContext.getContext();
		userData = (User)context.getSession().get("userData");
	}

	public void validate() {
		if(ordine.getId_ordine()<0) {
			addActionError(getText("errore.ordine.non_trovato"));
		}
		
		if(StringUtils.isBlank(ordine.getStato())) {
			addFieldError("stato", getText("fieldError.campo_vuoto"));
		}
		
		if(!StringUtils.isBlank(ordine.getData_spedizione())) {
			try {
				java.sql.Date.valueOf(ordine.getData_spedizione());
			} catch (IllegalArgumentException e) {
				addFieldError("data_spedizione", getText("fieldError.formato_errato"));
			}
		}
	}
	
	public String execute() {
		
		if(userData==null) { //??
			return LOGIN;
		}
		
		if(userData.getTipo()<2) { //cliente
			return "access_denied";
		}
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			ordineService.modificaOrdine(ordine, userData);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(getText("sql.generic"));
		} finally {
			ordineService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		addActionMessage(getText("ordine.modifica.success"));
		return SUCCESS;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

	@Override
	public Ordine getModel() {
		return ordine;
	}
	
}
