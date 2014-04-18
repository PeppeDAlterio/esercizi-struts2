package it.store.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Carrello {
	
	private List<Articolo> articoli = new ArrayList<Articolo>();
	
	private int numero_articoli = 0;
	
	private float prezzo_totale = 0.0f;

	public List<Articolo> getArticoli() {
		return this.articoli;
	}
	
	public void aggiungiArticolo(Articolo articolo) {
		this.articoli.add(articolo);
	}
	

	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}

	public int getNumero_articoli() {
		
		setNumero_articoli(articoli.size());
		
		return this.numero_articoli;
	}

	public void setNumero_articoli(int numero_articoli) {
		this.numero_articoli = numero_articoli;
	}

	public float getPrezzo_totale() {
		
		float prezzo_totale = 0.0f;
		
		for (Articolo art: articoli) {
			prezzo_totale += art.getPrezzo_finale()*art.getQuantita_ordinata();
		}
		
		DecimalFormat df = new DecimalFormat("###.00");
		String tmp = df.format(prezzo_totale);
		tmp = tmp.replace(",", ".");
		prezzo_totale = Float.parseFloat(tmp);
		
		setPrezzo_totale(prezzo_totale);
		
		return this.prezzo_totale;
	}

	public void setPrezzo_totale(float prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}
	
		
}
