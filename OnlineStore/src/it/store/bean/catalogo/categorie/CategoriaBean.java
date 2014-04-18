package it.store.bean.catalogo.categorie;

import it.store.service.CatalogoService;

import java.sql.SQLException;

public class CategoriaBean {

	private String categoria, parent;
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			this.parent = catalogoService.getParentCategoria(this.categoria);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			//FIXME: FIXME
		} finally {
			catalogoService = null;
		}
		
	}

	public String getParent() {		
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
}
