package it.store.action.area_admin;

import it.store.service.CatalogoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

/*
 * Action per "disegnare" le categorie in cui è diviso il catalogo.
 * Saranno poi passate alla view per mostrarle all'utente.
 */

@SuppressWarnings("serial")
public class MostraCategorieAction extends ActionSupport {
	
	//MultiMap <parent, sottoCategoria>
	public Map<String, List<String>> categorie = null;
	
	public List<String> listaCategorie = new ArrayList<>();

	public String getCategorieArticoli() {
		
		CatalogoService catalogoService;
		try {
			catalogoService = new CatalogoService();
			
			this.categorie = catalogoService.getCategorie();
			
		} catch (ClassNotFoundException e) {
			//TODO: Gestisci i messaggi d'errore!
			addActionError("errori.generico");
		} catch (SQLException e) {
			//TODO: ^
			addActionError("sql.generic");
		} finally {
			catalogoService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}

		/* inserisco le categorie presenti nella multimappa (mischiate) in una lista "ordinata" */
		toList(null);
						
		return SUCCESS;
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
	
	public void toList(String categoria) {		
		List<String> figli = this.categorie.get(categoria);
		
		// se la categoria non ha figli non esegui il for-each
		if(figli==null) return;
		// aggiungo una stringa di inizio-fine figli per permettere una miglior formattazione da parte della view
		else if (categoria!=null) listaCategorie.add("BEGIN_CHILDREN");
		
		for(String str: figli) {
			listaCategorie.add(str);
			
			toList(str);
		}
		
		// aggiungo una stringa di inizio-fine figli per permettere una miglior formattazione da parte della view
		if(categoria!=null) listaCategorie.add("END_CHILDREN");
		
		return;
	}
	
}
