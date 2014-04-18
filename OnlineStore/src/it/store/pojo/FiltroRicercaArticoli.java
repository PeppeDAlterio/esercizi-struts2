package it.store.pojo;

import org.apache.commons.lang3.StringUtils;

public class FiltroRicercaArticoli {
	
	private String idArticolo,
				   categoria,
				   marca,
				   nome,
				   codice_modello,
				   prezzo_min,
				   prezzo_max;
	
	private boolean scontato;
	
	/* i setters, settano la variabile a % se vuota per evitare problemi con il filtro durante la ricerca */
	
	public String getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(String idArticolo) {
		if(StringUtils.isBlank(idArticolo)) {
			idArticolo = "%";
		}
		
		this.idArticolo = idArticolo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
				
		if(StringUtils.isBlank(categoria)) {
			categoria = null;
		}
		
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if(StringUtils.isBlank(marca)) {
			marca = "%";
		}
		
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(StringUtils.isBlank(nome)) {
			nome = "%";
		}
		
		this.nome = nome;
	}

	public String getCodice_modello() {
		return codice_modello;
	}

	public void setCodice_modello(String codice_modello) {
		if(StringUtils.isBlank(codice_modello)) {
			codice_modello = "%";
		}
		
		this.codice_modello = codice_modello;
	}

	public String getPrezzo_min() {
		return prezzo_min;
	}

	public void setPrezzo_min(String prezzo_min) {
		if(StringUtils.isBlank(prezzo_min)) {
			prezzo_min = null;
		}
		this.prezzo_min = prezzo_min;
	}

	public String getPrezzo_max() {
		return prezzo_max;
	}

	public void setPrezzo_max(String prezzo_max) {
		if(StringUtils.isBlank(prezzo_max)) {
			prezzo_max = null;
		}
		this.prezzo_max = prezzo_max;
	}

	public boolean isScontato() {
		return scontato;
	}

	public void setScontato(boolean scontato) {
		this.scontato = scontato;
	}

}
