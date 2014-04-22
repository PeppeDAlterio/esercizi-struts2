package it.store.service;

import it.store.dto.Articolo;
import it.store.dto.Carrello;
import it.store.dto.Indirizzo;
import it.store.dto.Ordine;
import it.store.filtro.FiltroRicercaOrdini;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdineService extends DatabaseService {
	
	public OrdineService() throws ClassNotFoundException, SQLException {
		super();
	}
	
	public int nuovoOrdine(Ordine ordine) throws SQLException, ClassNotFoundException {
		
		//leggo data e ora
		Date dNow = new Date();
		// YYYY-MM-DD HH:MM:SS
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

		String datetime_ordine = format.format(dNow);
		
		//inserisco l'ordine nel DB
		String query = "INSERT INTO Ordine VALUES(0, ?, ?, NULL, ?, 'aperto', NULL, NULL)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, ordine.getUtente_email());
		statement.setString(2, datetime_ordine);
		statement.setFloat(3, ordine.getCarrello().getPrezzo_totale());
		statement.executeUpdate();
		
		System.out.println(statement.toString());
		
		statement.close();
		
		
		//leggo l'ID assegnato all'ordine
		int id;
		query = "SELECT id FROM Ordine WHERE Utente_email=? AND data=? AND totale=? AND stato='aperto'";
		statement = conn.prepareStatement(query);
		statement.setString(1, ordine.getUtente_email());
		statement.setString(2, datetime_ordine);
		statement.setFloat(3, ordine.getCarrello().getPrezzo_totale());
		ResultSet result = statement.executeQuery();
		
		System.out.println(statement.toString());
		
		if(result.next()) {
			id = result.getInt(1);
		} else { //errore
			
			result.close();
			statement.close();
			
			return -1;
		}
		
		result.close();
		statement.close();
		
		
		//inserisco gli articoli associati all'ordine
		query = "INSERT INTO Ordine_articoli VALUES (?, ?, ?, ?)";
		statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		for (Articolo art: ordine.getCarrello().getArticoli()) {
			statement.setInt(2, art.getIdArticolo());
			statement.setInt(3, art.getQuantita_ordinata());
			statement.setFloat(4, art.getPrezzo_finale());
			
			statement.executeUpdate();
			
			System.out.println(statement.toString());
		}
		statement.close();
		
		//rimuovo le quantità ordinate dalle quantità disponibili (articoli)
		CatalogoService catalogoService;
		catalogoService = new CatalogoService();
		for (Articolo art: ordine.getCarrello().getArticoli()) {
			art.setQuantita(art.getQuantita()-art.getQuantita_ordinata());
			catalogoService.modificaArticolo(art);
		}
		catalogoService = null;
		
		//inserisco l'indirizzo associato all'ordine
		query = "INSERT INTO Ordine_indirizzo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		statement.setString(2, ordine.getIndirizzo().nome_cognome);
		statement.setString(3, ordine.getIndirizzo().indirizzo_1);
		statement.setString(4, ordine.getIndirizzo().indirizzo_2);
		statement.setString(5, ordine.getIndirizzo().citta);
		statement.setString(6, ordine.getIndirizzo().provincia);
		statement.setString(7, ordine.getIndirizzo().cap);
		statement.setString(8, ordine.getIndirizzo().paese);
		statement.setString(9, ordine.getIndirizzo().telefono);
		statement.executeUpdate();
		
		System.out.println(statement.toString());
		
		statement.close();
		
		//aggiungo un recordo per il rilascio del feedback
		query = "INSERT INTO Feedback_ordine VALUES(?, ?, NULL, NULL, -2, NULL)";
		statement = conn.prepareStatement(query);
		statement.setString(1, ordine.getUtente_email());
		statement.setInt(2, id);
		statement.executeUpdate();
		
		statement.close();
		
		//ritorno in output l'id dell'ordine
		
		return id;
	}
	
	public int getNumeroPagineOrdini(String utente) throws SQLException {
		int numero_pagine = 0;
		
		String query = "SELECT COUNT(*) FROM Ordine WHERE Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, utente);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			numero_pagine = (int) Math.ceil(result.getInt(1)/5.0);
		}
		
		result.close();
		
		return numero_pagine;
		
	}

	public List<Ordine> getOrdiniCliente(String utente, int page) throws SQLException, ClassNotFoundException {
		List<Ordine> listaOrdini = new ArrayList<Ordine>();
		
		String query = "SELECT * FROM Ordine WHERE Utente_email=? ORDER BY data DESC LIMIT ?, 5";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, utente);
		statement.setInt(2, page*5);
		System.out.println(statement.toString());
		ResultSet result = statement.executeQuery();
		
		//inserisco i risultati in una lista
		Ordine tmp;
		while(result.next()) {
			tmp = new Ordine();
			riempi_dati_ordine(tmp, result);
			listaOrdini.add(tmp);
		}
		
		//se la lista è vuota ritorno null
		if(listaOrdini.isEmpty()) {
			listaOrdini = null;
		}
		
		return listaOrdini;
	}
	
	public Ordine getOrdineById(int id_ordine, String utente) throws SQLException, ClassNotFoundException {
		Ordine ordine = new Ordine();
		
		String query = "SELECT * FROM Ordine, Ordine_indirizzo WHERE id=? AND Utente_email=? AND id=Ordine_id";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id_ordine);
		statement.setString(2, utente);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			riempi_dati_ordine(ordine, result);
		} else {
			ordine = null;
		}
		
		result.close();
		
		return ordine;
	}
	
	//la funzione inserire i dati base dell'ordine e legge articoli e indirizzo da DB ed inserisce tutto in 'ordine'
	private void riempi_dati_ordine(Ordine ordine, ResultSet result) throws SQLException, ClassNotFoundException {
		ordine.setId_ordine(result.getInt("id"));
		ordine.setUtente_email(result.getString("Utente_email"));
		ordine.setData(result.getString("data"));
		ordine.setData_spedizione(result.getString("data_spedizione"));
		ordine.setTotale(result.getFloat("totale"));
		ordine.setStato(result.getString("stato"));
		ordine.setData_modifica(result.getString("data_modifica"));
		ordine.setEmail_modifica(result.getString("email_modifica"));
		Indirizzo indirizzo = new Indirizzo();
		indirizzo.nome_cognome 	= result.getString("nome_cognome");
		indirizzo.indirizzo_1  	= result.getString("indirizzo_1");
		indirizzo.indirizzo_2	= result.getString("indirizzo_2");
		indirizzo.citta			= result.getString("citta");
		indirizzo.provincia		= result.getString("provincia");
		indirizzo.cap			= result.getString("cap");
		indirizzo.paese			= result.getString("paese");
		indirizzo.telefono		= result.getString("telefono");
		ordine.setIndirizzo(indirizzo);
		
		//leggo gli ordini associati
		String query = "SELECT * FROM Ordine_articoli WHERE Ordine_id=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, ordine.getId_ordine());
		ResultSet res = statement.executeQuery();
		
		riempi_articoli_ordine(ordine, res);
		
		res.close();
		statement.close();
	}
	
	//inserisci gli articoli in una lista e la associa al carrello dell'ordine
	private void riempi_articoli_ordine(Ordine ordine, ResultSet result) throws SQLException, ClassNotFoundException {
		Carrello carrello = new Carrello();
		
		//articoli in lista!
		Articolo tmp;
		CatalogoService catalogoService = new CatalogoService();
		while(result.next()) {
			//leggo info articolo
			tmp = catalogoService.getArticoloById(result.getInt("Articolo_id"));
			
			//Inserisco prezzo d'acquisto e quantità ordinata
			tmp.setIdArticolo(result.getInt("Articolo_id"));
			tmp.setQuantita(result.getInt("quantita"));
			tmp.setPrezzo_finale(result.getFloat("prezzo_corrente"));
						
			carrello.aggiungiArticolo(tmp);
		}
		catalogoService = null;
		
		ordine.setCarrello(carrello);
	}
		
	/*
	 * @return: false=ok | true=errore: ordine già spedito o non trovato
	 */
	public boolean modificaIndirizzoOrdine(int id_ordine, String utente, Indirizzo indirizzo) throws SQLException {
		
		String query = "SELECT stato FROM Ordine WHERE id=? AND Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id_ordine);
		statement.setString(2, utente);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			if(result.getString(1).equals("spedito")) {
				result.close();
				statement.close();
				return true;
			}
		} else { //ordine non trovato
			result.close();
			statement.close();
			return true;
		}
		
		result.close();
		statement.close();
		
		//trovato e non ancora spedito
		query = "UPDATE Ordine_indirizzo SET nome_cognome=?, indirizzo_1=?, indirizzo_2=?, citta=?, provincia=?, cap=?, paese=?, telefono=? WHERE Ordine_id=?";
		statement = conn.prepareStatement(query);
		statement.setString(1, indirizzo.nome_cognome);
		statement.setString(2, indirizzo.indirizzo_1);
		statement.setString(3, indirizzo.indirizzo_2);
		statement.setString(4, indirizzo.citta);
		statement.setString(5, indirizzo.provincia);
		statement.setString(6, indirizzo.cap);
		statement.setString(7, indirizzo.paese);
		statement.setString(8, indirizzo.telefono);
		statement.setInt(9, id_ordine);
		statement.executeUpdate();
		
		statement.close();
		
		return false;
	}
	
	
	/*
	 * @return: numero pagine
	 */
	public int ricercaOrdini(FiltroRicercaOrdini filtro, int p, List<Ordine> risultati) throws SQLException {
		
		String query = "SELECT * FROM Ordine WHERE Utente_email LIKE ? AND DATE(data) LIKE ? AND stato LIKE ? ORDER BY data DESC LIMIT ?, 10";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, filtro.getUtente_email());
		statement.setString(2, filtro.getData());
		statement.setString(3, filtro.getStato());
		statement.setInt(4, p*10);
		
		ResultSet result = statement.executeQuery();
		
		Ordine tmp;
		while(result.next()) {
			tmp = new Ordine();
			tmp.setId_ordine(result.getInt("id"));
			tmp.setUtente_email(result.getString("Utente_email"));
			tmp.setData(result.getString("data"));
			tmp.setStato(result.getString("stato"));
			
			risultati.add(tmp);
		}
		
		
		//leggo lo statement dal precedente
		String str = statement.toString();
		query = str.substring(str.indexOf("SELECT"), str.lastIndexOf("ORDER"));
		
		//sostituisco il SELECT * con SELECT COUNT(*)
		query = query.replace("SELECT *", "SELECT COUNT(*)");
		
		result.close();
		statement.close();
		
		//leggo numero pagine
		int totale_pagine=0;
		statement = conn.prepareStatement(query);
		result = statement.executeQuery();
		
		if(result.next()) { //??
			totale_pagine = (int) Math.ceil(result.getInt(1)/10.0);
		}
		
		result.close();
		statement.close();
		
		return totale_pagine; 
	}

}
