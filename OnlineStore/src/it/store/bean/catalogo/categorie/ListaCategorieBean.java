package it.store.bean.catalogo.categorie;

import it.store.service.CatalogoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListaCategorieBean {

	private Map<String, List<String>> categorie;
	
	private List<String> listaStampabile;
	
	private List<String> listaCategorie;

	public Map<String, List<String>> getCategorie() {
		
		if(categorie==null) {
			
			CatalogoService catalogoService;
			try {
				catalogoService = new CatalogoService();
				
				this.categorie = catalogoService.getCategorie();
				
			} catch (ClassNotFoundException e) {
				//FIXME: Gestisci i messaggi d'errore!
				//addActionError("errori.generico");
			} catch (SQLException e) {
				//FIXME: ^
				//addActionError("sql.generic");
			} finally {
				catalogoService = null;
				/*
				if(hasErrors()) {
					return ERROR;
				}
				*/
			}
			
		}
		
		return categorie;
	}

	public void setCategorie(Map<String, List<String>> categorie) {
		this.categorie = categorie;
	}

	public List<String> getListaStampabile() {
		
		if(listaStampabile==null) {
			
			listaStampabile = new ArrayList<>();
			
			/* inserisco le categorie presenti nella multimappa (mischiate) in una lista "ordinata" */
			generaStruttura(null);
		}
		
		return listaStampabile;
	}

	public void setListaStampabile(List<String> listaCategorie) {
		this.listaStampabile = listaCategorie;
	}
	
	/*
	 * Inserisce le categorie presenti nella multimappa in una lista.
	 * Le categorie nella lista sono ordinate come in questo esempio:
	 * CATEGORIA_1
	 * 	SUB_CAT_1-1
	 * 		SUB_SUB_CAT_1-1
	 * 	SUB_CAT_1-2
	 * CATEGORIA_2
	 * 	SUB_CAT_2-1
	 * 		SUB_SUB_CAT_2-1.1
	 * 		SUB_SUB_CAT_2-1.2
	 * 	SUB_CAT_2-2
	 * 	SUB_CAT_2-3
	 * 
	 * ATTENZIONE: SARA' COMPITO DELLA VIEW GESTIRE LA FORMATTAZIONE DELLA STESSA (AD ESEMPIO IL PADDING PER LE SOTTOCATEGORIE)!
	 * 
	 */
	private void generaStruttura(String categoria) {		
		List<String> figli = getCategorie().get(categoria);
		
		// se la categoria non ha figli non esegui il for-each
		if(figli==null) return;
		// aggiungo una stringa di inizio-fine figli per permettere una miglior formattazione da parte della view
		else if (categoria!=null) listaStampabile.add("BEGIN_CHILDREN");
		
		for(String str: figli) {
			listaStampabile.add(str);
			
			generaStruttura(str);
		}
		
		// aggiungo una stringa di inizio-fine figli per permettere una miglior formattazione da parte della view
		if(categoria!=null) listaStampabile.add("END_CHILDREN");
		
		return;
	}

	
	public List<String> getListaCategorie() {
		
		if(listaCategorie==null) {
			listaCategorie = new ArrayList<String>();
			
			listaCategorie.add("");
			
			toList(null);
		}
		
		return listaCategorie;
	}
	
	private void toList(String categoria) {		
		List<String> figli = getCategorie().get(categoria);
		
		// se la categoria non ha figli non esegui il for-each
		if(figli==null) return;
		
		for(String str: figli) {
			listaCategorie.add(str);
			
			toList(str);
		}
				
		return;
	}
	

	public void setListaCategorie(List<String> listaCategorie) {
		this.listaCategorie = listaCategorie;
	}
	
}
