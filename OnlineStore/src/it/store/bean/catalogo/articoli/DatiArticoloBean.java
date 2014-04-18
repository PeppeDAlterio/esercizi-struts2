package it.store.bean.catalogo.articoli;

import java.sql.SQLException;

import it.store.dto.Articolo;
import it.store.service.CatalogoService;

public class DatiArticoloBean {
	
	private Articolo articolo = null;
	
	private int idArticolo = -1;
	
	
	public int getIdArticolo() {
		return this.idArticolo;
	}
	
	
	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			setArticolo(catalogoService.getArticoloById(getIdArticolo()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//FIXME: complete me :D
		} catch (SQLException e) {
			e.printStackTrace();
			//FIXME: complete me :D
		} finally {
			catalogoService = null;
			//TODO: (?)
		}
	}


	public Articolo getArticolo() {
		return articolo;
	}


	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	
}
