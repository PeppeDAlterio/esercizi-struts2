package it.store.action.area_cliente;

import it.store.dto.Ordine;
import it.store.dto.User;
import it.store.service.OrdineService;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ModificaOrdineAction extends ActionSupport implements Preparable {
	
	private User userData;
	private Ordine ordine;
	private int id_ordine = -1;
	
	@Override
	public void prepare() {
		
		ActionContext context = ActionContext.getContext();
		userData = (User)context.getSession().get("userData");
		
	}
	
	public void validate() {
		if(id_ordine<0) {
			addActionError(getText("errore.ordine.non_trovato"));
		}
	}
		
	public String execute() {
		
		if(userData==null) { // wtf?
			return LOGIN;
		}
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			this.ordine = ordineService.getOrdineById(id_ordine, userData.email, userData.getTipo());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(getText("errore.ordine.non_trovato"));
		} finally {
			ordineService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		if(ordine==null) { //ordine non trovato o non associato a quest'account
			addActionError(getText("errore.ordine.non_trovato"));
			return INPUT;
		} else {
			if(ordine.getStato().equals(getText("stato.spedito"))) {
				addActionError(getText("errore.ordine.gia_spedito"));
				return ERROR;
			}
		}
				
		return SUCCESS;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}
}
