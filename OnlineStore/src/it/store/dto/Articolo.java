package it.store.dto;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class Articolo {
	
	private int idArticolo = -1;
	
	private String categoria,
				   marca,
				   nome,
				   codice_modello,
				   descrizione;
	
	private int	quantita = 1,
				quantita_ordinata = 1, //campo utilizzato per gli ordini: indica quanti pezzi sono stati ordinati
				pezzi_venduti = 0;
	
	private float prezzo = 0,
				  sconto_perc = 0,
				  prezzo_finale;

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
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
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodice_modello() {
		return codice_modello;
	}

	public void setCodice_modello(String codice_modello) {
		this.codice_modello = codice_modello;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getQuantita_ordinata() {
		return quantita_ordinata;
	}

	public void setQuantita_ordinata(int quantita_ordinata) {
		
		if(quantita_ordinata>getQuantita()) { //se la quantita ordinata è superiore a quella disponibile, viene ordinato il massimo possibile
			quantita_ordinata = getQuantita();
		}
		
		this.quantita_ordinata = quantita_ordinata;
	}

	public int getPezzi_venduti() {
		return pezzi_venduti;
	}

	public void setPezzi_venduti(int pezzi_venduti) {
		this.pezzi_venduti = pezzi_venduti;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
		
		setPrezzo_finale(getPrezzo() - getPrezzo()*getSconto_perc()/100);
	}

	public float getSconto_perc() {
		return sconto_perc;
	}

	public void setSconto_perc(float sconto_perc) {
		this.sconto_perc = sconto_perc;
		
		DecimalFormat df = new DecimalFormat("###.00");
		
		float prezzo_finale = getPrezzo() - getPrezzo()*getSconto_perc()/100;
		
		String tmp = df.format(prezzo_finale);
		tmp = tmp.replace(",", ".");
		prezzo_finale = Float.parseFloat(tmp);
		
		setPrezzo_finale(prezzo_finale);
	}

	public float getPrezzo_finale() {
		return prezzo_finale;
	}

	public void setPrezzo_finale(float prezzo_finale) {
		this.prezzo_finale = prezzo_finale;
	}
	
}
