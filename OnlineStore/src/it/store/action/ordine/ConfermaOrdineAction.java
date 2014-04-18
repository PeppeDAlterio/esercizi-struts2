package it.store.action.ordine;

import java.sql.SQLException;

import it.store.dto.Ordine;
import it.store.dto.User;
import it.store.service.OrdineService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ConfermaOrdineAction extends ActionSupport implements Preparable {
	
	private Ordine ordine;
	private User userData;
	
	private int id_ordine = -1;
	
	@Override
	public void prepare() {
		ActionContext context = ActionContext.getContext();
		setOrdine( (Ordine)context.getSession().get("ordine") );
		
		setUserData( (User)context.getSession().get("userData") );
	}
	
	/*
	 * Metodo che controlla, prima di registrare l'ordine, che sia valido
	 */
	private String validate_ordine() {
		if(ordine==null) {
			return "access_denied";
		}
		
		if(ordine.getCarrello()==null) {
			return "access_denied";
		} else {
			if(ordine.getCarrello().getArticoli()==null) {
				return "access_denied";
			} else if (ordine.getCarrello().getArticoli().isEmpty()) {
				return "access_denied";
			}
		}
		
		if(ordine.getIndirizzo()==null) {
			return "access_denied";
		} else {
			if(!ordine.getIndirizzo().isValido()) {
				return "access_denied";
			}
		}
		
		return SUCCESS;
	}
	
	public String conferma() {
		
		if(getUserData()==null) {
			return "access_denied";
		} else {
			ordine.setUtente_email(getUserData().email);
		}
		
		String errore = validate_ordine();
		
		if(errore.equals("success")) {
			System.out.println("SUCCESS!!");
			OrdineService ordineService;
			try {
				ordineService = new OrdineService();
				id_ordine = ordineService.nuovoOrdine(getOrdine());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				//FIXME
			} catch (SQLException e) {
				e.printStackTrace();
				//FIXME
			} finally {
				ordineService = null;
				
				if(id_ordine==-1) {
					addActionError(getText("errore.ordine.generico"));
					return ERROR;
				}
			}
			
			//cancello l'ordine ed il carello dalla Session Scope
			ActionContext context = ActionContext.getContext();
			context.getSession().remove("ordine");
			context.getSession().remove("carrello");
			
			String[] str = {String.valueOf(id_ordine)};
			
			addActionMessage( getText("ordine.success", str) );
			
			return SUCCESS;
			
		} else {
			return errore;
		}
	}
	
	
	public String annulla() {
		//cancello l'ordine dalla session scope
		ActionContext context = ActionContext.getContext();
		context.getSession().remove("ordine");
		
		addActionMessage(getText("ordine.annullato"));
		
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

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

}
