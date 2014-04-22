package it.store.action.area_operatore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.store.dto.Articolo;
import it.store.filtro.FiltroRicercaArticoli;
import it.store.service.CatalogoService;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RicercaArticoliAction extends ActionSupport implements ModelDriven<FiltroRicercaArticoli> {
	
	public int p = 0;
	
	private int totale_pagine = -1;
	
	public List<Articolo> risultatiRicerca = new ArrayList<Articolo>();
	
	public FiltroRicercaArticoli filtro = new FiltroRicercaArticoli();
	
	public boolean soloScontati;
	
	public void validate() {
		
		if(!StringUtils.isBlank(filtro.getIdArticolo())) {
			if(!StringUtils.isNumeric(filtro.getIdArticolo()) && !filtro.getIdArticolo().equals("%")) {
				addFieldError("idArticolo", getText("fieldError.formato_errato"));
			}
		}
		
		if(!StringUtils.isBlank(filtro.getPrezzo_min())) {
			if(!StringUtils.isNumeric(filtro.getPrezzo_min())) {
				addFieldError("prezzo_da", getText("fieldError.formato_errato"));
			}
		}
		
		if(!StringUtils.isBlank(filtro.getPrezzo_max())) {
			if(!StringUtils.isNumeric(filtro.getPrezzo_max())) {
				addFieldError("prezzo_a", getText("fieldError.formato_errato"));
			}
		}
		
	}
	
	public String execute() {
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
//TODO
			totale_pagine = catalogoService.ricercaArticoli(filtro, p, risultatiRicerca);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			addActionError(getText("errori.generico"));
		} catch (SQLException e ) {
			e.printStackTrace();
//FIXME !!
			addActionError(getText("sql.generic"));
		} finally {
			catalogoService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		if(risultatiRicerca.isEmpty()) {
			risultatiRicerca = null;
		}
				
		return SUCCESS;
	}

	public int getTotale_pagine() {
		return totale_pagine;
	}

	@Override
	public FiltroRicercaArticoli getModel() {
		return filtro;
	}

}
