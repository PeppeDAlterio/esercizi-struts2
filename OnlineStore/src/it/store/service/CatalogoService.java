package it.store.service;

import it.store.dto.Articolo;
import it.store.filtro.FiltroRicercaArticoli;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoService extends DatabaseService {

	public CatalogoService() throws ClassNotFoundException, SQLException {
		super();
	}
	
	//MultiMap <parent, sottoCategoria>
	public Map<String, List<String>> getCategorie() throws SQLException {
		
		//Map<Parent, Categoria>
		Map<String, List<String>> categorie = new HashMap<>();
		
		String query = "SELECT * FROM Categorie_articoli";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		String parent;
		List<String> sottoCategorie;
		while(result.next()) {
			//leggo il nome della categoria madre
			parent 		= result.getString("parent");
			
			//leggo la lista delle sottocategorie per questa "super-categoria"
			sottoCategorie = categorie.get(parent);
			
			//se non sono già presenti sottocategorie, crea la lista
			if(sottoCategorie == null) {
				sottoCategorie = new ArrayList<>();
			}
			
			//aggiungo la sottocategoria alla lista
			sottoCategorie.add(result.getString("categoria"));
			
			//aggiungo il dato nella MultiMap
			categorie.put(parent, sottoCategorie);
		}
		
		if(categorie.isEmpty()) {
			categorie = null;
		}
		
		result.close();
		statement.close();
		
		return categorie;
	}

	private String validate_aggiungiCategoria(String categoria, String parent) throws SQLException {
		
		String errore = null;
		//parent è null, il parent è valido (non è sottocategoria)
		if(parent==null) return null;
		
		String query = "SELECT * FROM Categorie_articoli WHERE categoria=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, parent);
		ResultSet result = statement.executeQuery();
//TODO: PROVAMI
		if(!result.next()) {
			errore = "sql.categorie.wrong_parent";
		}
		
		result.close();
		statement.close();
		
		return errore;
	}
	
	public String aggiungiCategoria(String categoria, String parent) throws SQLException {
		String validate;
		validate = validate_aggiungiCategoria(categoria, parent);
		
		if(validate==null) {
			
			String query = "INSERT INTO Categorie_articoli VALUES(?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, categoria);
			statement.setString(2, parent);
			statement.executeUpdate();
			
			statement.close();
			
			return null;
			
		} else {
			return validate;
		}
		
	}

	public String rimuoviCategoria(String categoria) throws SQLException {
		String query = "DELETE FROM Categorie_articoli WHERE categoria=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, categoria);
		statement.executeUpdate();
		
		statement.close();
		
		//cancello le sottocategorie
		query = "DELETE FROM Categorie_articoli WHERE parent=?";
		statement = conn.prepareStatement(query);
		statement.setString(1, categoria);
		statement.executeUpdate();
		
		statement.close();
		
		return null;
	}
	
	public boolean modificaCategoria(String categoria, String nuovo_nome, String nuovo_parent) throws SQLException {
		
		int errore;
		
		//modifico la categoria
		String query = "UPDATE Categorie_articoli SET categoria=?, parent=? WHERE categoria=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nuovo_nome);
		statement.setString(2, nuovo_parent);
		statement.setString(3, categoria);
		errore = statement.executeUpdate();
		
		statement.close();
		
		if(errore==0) {
			return false;
		}
		
		//modifico il parent delle sue sottocategoria
		query = "UPDATE Categorie_articoli SET parent=? WHERE parent=?";
		statement = conn.prepareStatement(query);
		statement.setString(1, nuovo_nome);
		statement.setString(2, categoria);
		statement.executeUpdate();
		
		statement.close();
		
		//TODO: PROVAMI
		
		
		query = "UPDATE Articolo SET categoria=? WHERE categoria=?";
		statement = conn.prepareStatement(query);
		statement.setString(1, nuovo_nome);
		statement.setString(2, categoria);
		statement.executeUpdate();
		
		statement.close();
		
		return true;
	}
	
	public String getParentCategoria(String categoria) throws SQLException {
		String parent = null;
		
		String query = "SELECT parent FROM Categorie_articoli WHERE categoria=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, categoria);
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			parent = result.getString(1);
		}
		
		result.close();
		
		return parent;
	}
	
	public void aggiungiArticolo(Articolo articolo) throws SQLException {
		
//FIXME: Aggiungi check multipli
		String query = "INSERT INTO Articolo VALUES (0, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, articolo.getCategoria());
		statement.setString(2, articolo.getMarca());
		statement.setString(3, articolo.getNome());
		statement.setString(4, articolo.getCodice_modello());
		statement.setString(5, articolo.getDescrizione());
		statement.setInt(6, articolo.getQuantita());
		statement.setFloat(7, articolo.getPrezzo());
		statement.setFloat(8, articolo.getSconto_perc());
		statement.setFloat(9, articolo.getPrezzo_finale());
		statement.executeUpdate();
		
		statement.close();
		
	}
	
	/*
	 * @return:
	 * 		false = articolo non trovato
	 * 		true  = Ok!
	 */
	public boolean modificaArticolo(Articolo articolo) throws SQLException {
		String query = "UPDATE Articolo SET categoria=?, descrizione=?, quantita=?, prezzo=?, sconto_perc=?, prezzo_finale=? WHERE id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, articolo.getCategoria());
		statement.setString(2, articolo.getDescrizione());
		statement.setInt(3, articolo.getQuantita());
		statement.setFloat(4, articolo.getPrezzo());
		statement.setFloat(5, articolo.getSconto_perc());
		statement.setFloat(6, articolo.getPrezzo_finale());
		statement.setInt(7, articolo.getIdArticolo());
		
		System.out.println(statement.toString());
		
		int errore = statement.executeUpdate();
		
		statement.close();
		
		if(errore==0) {
			return false;
		} else {
			
			return true;
		}
	}
	
	public Articolo getArticoloById(int id) throws SQLException {
		Articolo articolo = new Articolo();
		
		String query = "SELECT * FROM Articolo WHERE id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			riempiArticolo(result, articolo);
		} else {
			articolo = null;
		}
		
		result.close();
		statement.close();
		
		return articolo;
	}
	
	public int ricercaArticoli(FiltroRicercaArticoli filtro, int numeroPagina, List<Articolo> risultatiRicerca) throws SQLException {
		
		int numero_pagine = 0;

		Articolo tmp; //oggetto buffer per gli articoli letti
		
		/*
		 * query : Query per leggere gli articoli
		 * query_count: conta il numero di articoli (per calcolare le pagine)
		 * where_clause: --
		 */
		String query, query_count, where_clause;
		
		if(filtro.getCategoria()==null || filtro.getCategoria().equals("%")) { //tutte le categorie o solo "super-categorie"
			where_clause = "WHERE (categoria LIKE ? OR categoria IS NULL) "
						 + "AND marca LIKE ? AND nome LIKE ? AND codice_modello LIKE ? AND (prezzo_finale>? AND prezzo_finale<?) AND sconto_perc>?";
		} else {
			where_clause = "WHERE categoria LIKE ? "
						+ "AND marca LIKE ? AND nome LIKE ? AND codice_modello LIKE ? AND (prezzo_finale>? AND prezzo_finale<?) AND sconto_perc>?";
		}
		
		query = "SELECT * FROM Articolo "+where_clause+" LIMIT ?, 10";
				
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, filtro.getCategoria());
		statement.setString(2, filtro.getMarca());
		statement.setString(3, filtro.getNome());
		statement.setString(4, filtro.getCodice_modello());
		statement.setFloat(5, Float.parseFloat(filtro.getPrezzo_min()));
		statement.setFloat(6, Float.parseFloat(filtro.getPrezzo_max()));

		if(filtro.isScontato()) {
			statement.setFloat(7, 0.0f);
		} else {
			statement.setFloat(7, -1.0f);
		}
		
		statement.setInt(8, numeroPagina*10);
		
				
		ResultSet result = statement.executeQuery();
		
		//riempio la lista con i dati letti da DB
		while(result.next()) {
			tmp = new Articolo();
			
			riempiArticolo(result, tmp);
			
			risultatiRicerca.add(tmp);
		}
						
		result.close();

		query_count = "SELECT COUNT(*) FROM Articolo ";
		
		//leggo la where clause con i dati già settati dallo statement appena eseguito
		where_clause = statement.toString().substring(statement.toString().indexOf("WHERE"), statement.toString().lastIndexOf("LIMIT"));
		
		statement.close();
		
		//aggiungo il SELECT COUNT(*) FROM Articolo alla where clause
		query_count = query_count.concat(where_clause);
						
		Statement stmt = conn.createStatement();
		result = stmt.executeQuery(query_count);
		
		if(result.next()) {
			
			numero_pagine = (int) Math.ceil(result.getInt(1)/10.0);
		}
		
		result.close();
		stmt.close();
						
		return numero_pagine;
	}
	
	public int ricercaArticoli_cliente(FiltroRicercaArticoli filtro, int page, List<Articolo> listaArticoli) throws SQLException {
		
		//verifico il filtro di ricerca
		filtro.validate();
		
		String query;
		
		if(filtro.getCategoria()==null || filtro.getCategoria().equals("%")) {
			query = "SELECT * FROM Articolo WHERE marca LIKE ? AND nome LIKE ? AND (categoria LIKE ? OR categoria IS NULL)"
					+ " AND (prezzo_finale<? AND prezzo_finale>?) AND quantita>0 LIMIT ?, 10";
		} else {
			query = "SELECT * FROM Articolo WHERE marca LIKE ? AND nome LIKE ? AND categoria LIKE ? "
					+ " AND (prezzo_finale<? AND prezzo_finale>?) AND quantita>0 LIMIT ?, 10";
		}
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, filtro.getMarca());
		statement.setString(2, filtro.getNome());
		statement.setString(3, filtro.getCategoria());
		statement.setString(4, filtro.getPrezzo_max());
		statement.setString(5, filtro.getPrezzo_min());
		statement.setInt(6, page*10);
		
		ResultSet result = statement.executeQuery();
		
		Articolo tmp;
		while(result.next()) {
			tmp = new Articolo();
			
			riempiArticolo(result, tmp);
			
			listaArticoli.add(tmp);
		}
		
		result.close();
		statement.close();
		
		//leggo numero pagine totali:
		int totale_pagine = 0;
		
		query = "SELECT COUNT(*) " + query.subSequence(query.indexOf("FROM"), query.lastIndexOf("LIMIT"));
	
		statement = conn.prepareStatement(query);
		statement.setString(1, filtro.getMarca());
		statement.setString(2, filtro.getNome());
		statement.setString(3, filtro.getCategoria());
		statement.setString(4, filtro.getPrezzo_max());
		statement.setString(5, filtro.getPrezzo_min());
		
		result = statement.executeQuery();
		
		if(result.next()) { //???
			totale_pagine = (int) Math.ceil(result.getInt(1)/10.0);
		}
		
		result.close();
		statement.close();
		
		return totale_pagine;
	}
	
	private void riempiArticolo(ResultSet result, Articolo articolo) throws SQLException {
		articolo.setIdArticolo(result.getInt(1));
		articolo.setCategoria(result.getString(2));
		articolo.setMarca(result.getString(3));
		articolo.setNome(result.getString(4));
		articolo.setCodice_modello(result.getString(5));
		articolo.setDescrizione(result.getString(6));
		articolo.setQuantita(result.getInt(7));
		articolo.setPrezzo(result.getFloat(8));
		articolo.setPezzi_venduti(result.getInt(9));
		articolo.setSconto_perc(result.getFloat(10));
		articolo.setPrezzo_finale(result.getFloat(11));
	}
}
