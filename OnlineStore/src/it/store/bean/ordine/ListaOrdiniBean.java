package it.store.bean.ordine;

import it.store.dto.Ordine;
import it.store.service.OrdineService;

import java.sql.SQLException;
import java.util.List;

public class ListaOrdiniBean {
	
	private List<Ordine> listaOrdini = null;
	private String utente;
	
	private int page = 0;
	
	private int pagine_totali = 0;

	public List<Ordine> getListaOrdini() {
		
		//carico la lista degli ordini per questo utente
		OrdineService ordiniService;
		try {
			ordiniService = new OrdineService();
			this.listaOrdini = ordiniService.getOrdiniCliente(utente, page);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		} finally {
			ordiniService = null;
		}
				
		return listaOrdini;
	}

	public void setListaOrdini(List<Ordine> listaOrdini) {
		this.listaOrdini = listaOrdini;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public int getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = Integer.parseInt(page);
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagine_totali() {
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			this.pagine_totali = ordineService.getNumeroPagineOrdini(utente);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		} finally {
			ordineService = null;
		}
		
		return pagine_totali;
	}

	public void setPagine_totali(int pagine_totali) {
		this.pagine_totali = pagine_totali;
	}

}
