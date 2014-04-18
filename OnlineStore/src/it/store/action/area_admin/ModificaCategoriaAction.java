package it.store.action.area_admin;

import java.sql.SQLException;

import it.store.service.CatalogoService;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ModificaCategoriaAction extends ActionSupport {
	
	public String categoria,
				  parent,
				  nuovo_nome,
				  nuovo_parent;
	
	public void validate() {
		if(StringUtils.isBlank(nuovo_nome)) {
			addFieldError("nuovo_nome", getText("fieldError.campo_vuoto"));
		}
		
		if(StringUtils.isBlank(nuovo_parent)) {
			nuovo_parent = null;
		}
	}
	
	public String execute() {
		
		//true=ok | false=errore
		boolean result = true;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			result = catalogoService.modificaCategoria(categoria, nuovo_nome, nuovo_parent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e ) {
			e.printStackTrace();
			//FIXME
			addActionError(getText("sql.generic"));
		} finally {
			catalogoService = null;
			
			if(hasErrors() || result==false) {
				addActionError(getText("sql.categorie.not_found"));
				return ERROR;
			}
		}
		
		return SUCCESS;
	}

}
