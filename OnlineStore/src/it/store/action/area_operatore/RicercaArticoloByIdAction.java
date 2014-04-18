package it.store.action.area_operatore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.store.dto.Articolo;
import it.store.service.CatalogoService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RicercaArticoloByIdAction extends ActionSupport {
	
	public int idArticolo = -1;
	
	public List<Articolo> risultatiRicerca = null;
		
	public void validate() {
		if(idArticolo<=0) {
			addFieldError("idArticolo", getText("fieldError.formato_errato"));
		}
	}
	
	public String execute() {
		
		Articolo articolo = null;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			articolo = catalogoService.getArticoloById(idArticolo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			
			addActionError(getText("sql.generic"));
		} finally {
			catalogoService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		if(articolo==null) {
			addActionError(getText("sql.articoli.not_found"));
		} else {
			risultatiRicerca = new ArrayList<Articolo>();
			risultatiRicerca.add(articolo);
		}
		
		return SUCCESS;
	}

}
