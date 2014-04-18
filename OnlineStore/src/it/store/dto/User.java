package it.store.dto;

import it.store.utils.MD5Encoder;

import org.apache.commons.lang3.StringUtils;

/*
 * Data Transfer Object per l'account utente (viene memorizzato in session)
 */

public class User {
	
	public User() {}
	
	private int tipo;
	public String tipo_stringa;
	
	public String email,
				  userId,
				  nome,
				  cognome,
				  telefono_fisso,
				  telefono_mobile,
				  email_secondaria;
	
	private String password,
	   			   codice_fiscale;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		
		if(password==null) {
			this.password = null;
		} else {
			MD5Encoder md5 = new MD5Encoder();
			this.password = md5.encodeString(password);
			md5 = null;
		}
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = StringUtils.upperCase(codice_fiscale);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
		
		switch(tipo) {
			case 1:
				this.tipo_stringa = "Cliente";
				break;
			case 2:
				this.tipo_stringa = "Operatore";
				break;
			case 3:
				this.tipo_stringa = "Amministratore";
				break;
		}
	}

}
