package it.store.action.area_cliente;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.Indirizzo;
import it.store.dto.User;
import it.store.service.OrdineService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ModificaIndirizzoOrdineAction extends ActionSupport implements ModelDriven<Indirizzo> {
	
	public int id_ordine = -1;
	
	private Indirizzo indirizzo = new Indirizzo();
		
	public void validate() {
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
	
	public String execute() {
		
		ActionContext context = ActionContext.getContext();
		User userData = (User)context.getSession().get("userData");
		
		if(userData==null) { // ??
			return LOGIN;
		}
		
		boolean errore=false; //true=ordine già spedito, non modificabile | false=ok
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			errore = ordineService.modificaIndirizzoOrdine(id_ordine, userData.email, userData.getTipo(), indirizzo);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} finally {
			ordineService = null;
			
			if(errore) { //Ordine già spedito o non trovato
				addActionError(getText("errore.ordine.gia_spedito"));
			}
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		
		return SUCCESS;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public Indirizzo getModel() {
		return indirizzo;
	}
	
}
