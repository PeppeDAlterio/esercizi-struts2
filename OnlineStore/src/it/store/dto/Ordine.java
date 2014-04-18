package it.store.dto;

public class Ordine {
	
	private int id_ordine = -1;
	private String Utente_email,
				   data,
				   data_spedizione,
				   stato,
				   data_modifica,
				   email_modifica;
	
	private float totale;
	
	private Carrello carrello;
	
	private Indirizzo indirizzo;
	
	
	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public String getUtente_email() {
		return Utente_email;
	}

	public void setUtente_email(String utente_email) {
		Utente_email = utente_email;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData_spedizione() {
		return data_spedizione;
	}

	public void setData_spedizione(String data_spedizione) {
		this.data_spedizione = data_spedizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {		
		this.stato = stato;
	}

	public String getData_modifica() {
		return data_modifica;
	}

	public void setData_modifica(String data_modifica) {
		this.data_modifica = data_modifica;
	}

	public String getEmail_modifica() {
		return email_modifica;
	}

	public void setEmail_modifica(String email_modifica) {
		this.email_modifica = email_modifica;
	}

	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

}
