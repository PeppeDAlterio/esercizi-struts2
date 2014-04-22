package it.store.bean.ordine;

import java.sql.SQLException;

import it.store.dto.Ordine;
import it.store.service.OrdineService;

public class DatiOrdineBean {
	
	private Ordine ordine;
	private String utente;
	private int id_ordine = -1;
	
	private int tipoAccount = 1;
	
	public Ordine getOrdine() {
		return ordine;
	}
	
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	
	public String getUtente() {
		return utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public int getId_ordine() {
		return id_ordine;
	}
	
	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			this.ordine = ordineService.getOrdineById(id_ordine, utente, tipoAccount);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		} finally {
			ordineService = null;
		}
	}

	public int getTipoAccount() {
		return tipoAccount;
	}

	public void setTipoAccount(int tipoAccount) {
		this.tipoAccount = tipoAccount;
	}
	
}
