package it.store.dto;

public class Feedback {
	
	private String Utente_email,
				   messaggio,
				   approvato_da;
	
	private int id_ordine,
				punteggio,
				stato; /*
						*	-2: NON RILASCIATO
						*	-1: IN APPROVAZIONE
						*	 0: NON APPROVATO
						*	 1: APPROVATO
						*/

	public String getUtente_email() {
		return Utente_email;
	}

	public void setUtente_email(String utente_email) {
		Utente_email = utente_email;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public String getApprovato_da() {
		return approvato_da;
	}

	public void setApprovato_da(String approvato_da) {
		this.approvato_da = approvato_da;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int ordine_id) {
		this.id_ordine = ordine_id;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}
	
	

}
