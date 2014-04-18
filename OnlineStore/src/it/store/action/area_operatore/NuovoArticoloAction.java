package it.store.action.area_operatore;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import it.store.dto.Articolo;
import it.store.service.CatalogoService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class NuovoArticoloAction extends ActionSupport implements ModelDriven<Articolo> {
	
	public Articolo articolo = new Articolo();
	
	public void validate() {
		if(StringUtils.isBlank(articolo.getMarca())) {
			addFieldError("marca", getText("fieldError.campo_vuoto"));
		}
		
		if(StringUtils.isBlank(articolo.getNome())) {
			addFieldError("nome", getText("fieldError.campo_vuoto"));
		}
		
		if(articolo.getQuantita()<0) {
			addFieldError("quantita", getText("fieldError.formato_errato"));
		}
		
		if(articolo.getPrezzo()<0) {
			addFieldError("prezzo", getText("fieldError.formato_errato"));
		}
		
		if(! (articolo.getSconto_perc()>=0 && articolo.getSconto_perc()<=100) ) {
			addFieldError("sconto_perc", getText("fieldError.formato_errato"));
		}
	}
	
	public String execute() {
		
		boolean stato=true; //articolo non trovato in caso di modifica: true=ok | false=errore
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			if(articolo.getIdArticolo()==-1) { //AGGIUNTA ARTICOLO
				catalogoService.aggiungiArticolo(articolo);
			}
			else { //MODIFICA ARTICOLO
				stato = catalogoService.modificaArticolo(articolo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
//TODO
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(getText("sql.generic"));
//TODO
		} finally {
			catalogoService = null;
			
			if(!stato) {
				addActionError(getText("sql.articoli.not_found"));
			}
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		addActionMessage(getText("aggiuntaArticoli.success"));
		
		return SUCCESS;
	}

	@Override
	public Articolo getModel() {
		return articolo;
	}

}
