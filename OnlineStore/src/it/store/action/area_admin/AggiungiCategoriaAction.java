package it.store.action.area_admin;

import it.store.service.CatalogoService;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AggiungiCategoriaAction extends ActionSupport {
	
	public String categoria;
	public String parent;
	
	public String rimuovi = null;
	
	public void validate() {		
		if(rimuovi==null) {
			if(StringUtils.isBlank(categoria)) {
				addFieldError("categoria", getText("fieldError.campo_vuoto"));
			}
						
			if(StringUtils.isBlank(parent)) {
				this.parent = null;
			}
			
		}
	}
//TODO: DOCUMENTA E PROVA :D
	public String aggiungiCategoria() {
		
		String errore = null;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			errore = catalogoService.aggiungiCategoria(categoria, parent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
//FIXME: Gestisci i messaggi!!
			addActionError(getText("sql.generic"));
		} finally {
			catalogoService = null;
			
			if(errore!=null) {
				addActionError(errore);
			}
			
			if(hasErrors()) {
				return ERROR;
			}
			
		}
		
		addActionMessage(getText("categoria.agg_success"));
		
		return SUCCESS;
		
	}
	
	public String rimuoviCategoria() {
		
		String errore = null;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			errore = catalogoService.rimuoviCategoria(rimuovi);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
//FIXME: Gestisci i messaggi!!
			addActionError(getText("errori.generico"));
		} finally {
			catalogoService = null;
			
			if(errore!=null) {
				addActionError(errore);
			}
			
			if(hasErrors()) {
				return ERROR;
			}
			
		}
		
		addActionMessage(getText("categoria.rim_success"));
		
		return SUCCESS;
	}
}
