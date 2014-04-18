package it.store.action.ordine;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.Carrello;
import it.store.dto.Indirizzo;
import it.store.dto.Ordine;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class GestioneDatiOrdineAction extends ActionSupport implements ModelDriven<Indirizzo> {
	
	private Indirizzo indirizzo = new Indirizzo();
	
	public String setCarrello() {
		
		ActionContext context = ActionContext.getContext();
		Carrello carrello = (Carrello)context.getSession().get("carrello");
		
		if(carrello==null) {
			addActionError(getText("errore.ordine.carrello_vuoto"));
			return INPUT;
		} else {
			if(carrello.getArticoli().isEmpty()) {
				addActionError(getText("errore.ordine.carrello_vuoto"));
				return INPUT;
			}
			
			Ordine ordine = new Ordine();
			ordine.setCarrello(carrello);
			
			context.getSession().put("ordine", ordine);
			
			return SUCCESS;
		}
	}
	
	public String setIndirizzo() {
		ActionContext context = ActionContext.getContext();
		Ordine ordine = (Ordine)context.getSession().get("ordine");
		
		if(ordine==null) {
			return "access_denied";
		} else {
			if(ordine.getCarrello()==null) {
				return "access_denied";
			} else if (ordine.getCarrello().getArticoli().isEmpty()) {
				return "access_denied";
			}
			
			validate_indirizzo();
			
			if(hasErrors()) {
				return INPUT;
			}
			
			ordine.setIndirizzo(indirizzo);
			
			return SUCCESS;
			
		}
	}
	
	private void validate_indirizzo() {		
		if (StringUtils.isBlank(indirizzo.nome_cognome)) {
			addFieldError("nome_cognome", getText("fieldError.campo_vuoto"));
		}
		
		if (StringUtils.isBlank(indirizzo.indirizzo_1)) {
			addFieldError("indirizzo_1", getText("fieldError.campo_vuoto"));
		}
		
		if (StringUtils.isBlank(indirizzo.citta)) {
			addFieldError("citta", getText("fieldError.campo_vuoto"));
		}
		
		if (StringUtils.isBlank(indirizzo.provincia)) {
			addFieldError("provincia", getText("fieldError.campo_vuoto"));
		}
		
		if (StringUtils.isNumeric(indirizzo.cap)) {
			if(indirizzo.cap.length()!=5) {
				addFieldError("cap", getText("fieldError.formato_errato"));
			}
		}
		
		if (StringUtils.isBlank(indirizzo.paese)) {
			addFieldError("paese", getText("fieldError.campo_vuoto"));
		}
		
		if (!StringUtils.isBlank(indirizzo.telefono)) {
			if(indirizzo.telefono.length()<9 || !StringUtils.isNumeric(indirizzo.telefono)) {
				addFieldError("telefono", getText("fieldError.formato_errato"));
			}
		}
	}

	@Override
	public Indirizzo getModel() {
		return indirizzo;
	}
	
}
