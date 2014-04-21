package it.store.filtro;

import org.apache.commons.lang3.StringUtils;

public class FiltroRicercaOrdini {

	private String utente_email,
				   data,
				   stato;

	public String getUtente_email() {
		return utente_email;
	}

	public void setUtente_email(String utente_email) {
		if(StringUtils.isBlank(utente_email)) {
			utente_email = "%";
		}
		this.utente_email = utente_email;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		if(StringUtils.isBlank(data)) {
			data = "%";
		}
		this.data = data;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		if(StringUtils.isBlank(stato)) {
			stato = "%";
		}
		this.stato = stato;
	}
	
}
