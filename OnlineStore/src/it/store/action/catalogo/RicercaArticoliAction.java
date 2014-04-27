package it.store.action.catalogo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.store.dto.Articolo;
import it.store.filtro.FiltroRicercaArticoli;
import it.store.service.CatalogoService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RicercaArticoliAction extends ActionSupport implements ModelDriven<FiltroRicercaArticoli> {
	
	public int page = 0,
			   totale_pagine = 0;
	
	private FiltroRicercaArticoli filtro = new FiltroRicercaArticoli();
	
	private List<Articolo> listaArticoli = new ArrayList<Articolo>();
	
	public String execute() {
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			totale_pagine = catalogoService.ricercaArticoli_cliente(filtro, page, listaArticoli);
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
		
		if(listaArticoli.isEmpty()) {
			listaArticoli = null;
		}
		
		return SUCCESS;
	}

	public FiltroRicercaArticoli getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroRicercaArticoli filtro) {
		this.filtro = filtro;
	}

	public List<Articolo> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<Articolo> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}

	@Override
	public FiltroRicercaArticoli getModel() {
		return filtro;
	}

}
