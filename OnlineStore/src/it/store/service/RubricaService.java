package it.store.service;

import it.store.dto.Indirizzo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RubricaService extends DatabaseService {

	public RubricaService() throws ClassNotFoundException, SQLException {
		super();
	}

	public int aggiungiModificaIndirizzo(Indirizzo indirizzo) throws SQLException {	
		String query;
		PreparedStatement statement;
		if(indirizzo.id_indirizzo==-1) { /* nuovo indirizzo */
			query = "INSERT INTO Utente_indirizzi VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
			statement.setString(1, indirizzo.email);
			statement.setString(2, indirizzo.nome_cognome);
			statement.setString(3, indirizzo.indirizzo_1);
			statement.setString(4, indirizzo.indirizzo_2);
			statement.setString(5, indirizzo.citta);
			statement.setString(6, indirizzo.provincia);
			statement.setString(7, indirizzo.cap);
			statement.setString(8, indirizzo.paese);
			statement.setString(9, indirizzo.telefono);
		} else {	/* modifica indirizzo pre-esistente */
			query = "UPDATE Utente_indirizzi SET nome_cognome=?, indirizzo_1=?, indirizzo_2=?, citta=?, provincia=?,"
					+ " cap=?, paese=?, telefono=? WHERE id=?";
			statement = conn.prepareStatement(query);
			statement.setString(1, indirizzo.nome_cognome);
			statement.setString(2, indirizzo.indirizzo_1);
			statement.setString(3, indirizzo.indirizzo_2);
			statement.setString(4, indirizzo.citta);
			statement.setString(5, indirizzo.provincia);
			statement.setString(6, indirizzo.cap);
			statement.setString(7, indirizzo.paese);
			statement.setString(8, indirizzo.telefono);
			statement.setInt(9, indirizzo.id_indirizzo);
		}
		
		int errore = statement.executeUpdate();
		statement.close();
		
		return errore;
	}
	
	/*
	 * Leggo tutti gli indirizzi associati a questa email e li ritorno in output in una lista
	 */
	public List<Indirizzo> getRubricaUtente(String email) throws SQLException {
		
		List<Indirizzo> rubrica = new ArrayList<Indirizzo>();
		
		Indirizzo temp = null;
		
		String query = "SELECT * FROM Utente_indirizzi WHERE Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		
		/* inserisco tutti gli indirizzi letti nella lista */
		while(result.next()) {
			temp = new Indirizzo();
			temp.id_indirizzo 	= result.getInt("id");
			temp.nome_cognome 	= result.getString("nome_cognome");
			temp.indirizzo_1  	= result.getString("indirizzo_1");
			temp.indirizzo_2	= result.getString("indirizzo_2");
			temp.citta			= result.getString("citta");
			temp.provincia		= result.getString("provincia");
			temp.cap			= result.getString("cap");
			temp.paese			= result.getString("paese");
			temp.telefono		= result.getString("telefono");
			
			rubrica.add(temp);
		}
		
		/* se la lista e' vuota, la setto a null (per coonvenzione!) */
		if(rubrica.isEmpty()) {
			rubrica = null;
		}
		
		/* chiudo il resultset e il prepstatement */
		result.close();
		statement.close();
		
		return rubrica;
	}
	
	/* Leggo l'indirizzo associato all'account tramite ID */
	public boolean getIndirizzoById(Indirizzo indirizzo, String email) throws SQLException {
		
		boolean valido; //flag che indica se l'indirizzo è stato caricato
		
		String query = "SELECT * FROM Utente_indirizzi WHERE id=? AND Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, indirizzo.id_indirizzo);
		statement.setString(2, email);
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			indirizzo.nome_cognome	= result.getString("nome_cognome");
			indirizzo.indirizzo_1	= result.getString("indirizzo_1");
			indirizzo.indirizzo_2	= result.getString("indirizzo_2");
			indirizzo.citta			= result.getString("citta");
			indirizzo.provincia		= result.getString("provincia");
			indirizzo.cap			= result.getString("cap");
			indirizzo.paese			= result.getString("paese");
			indirizzo.telefono		= result.getString("telefono");
			
			valido = true;
		} else {
			valido = false;
		}
		
		result.close();		
		statement.close();
		
		return valido;
	}
	
	/* 
	 * metodo per rimuovere un indirizzo dalla rubrica tramite id (email necessaria per non cancellare indirizzi altrui)
	 */
	public int rimuoviIndirizzo(int id_indirizzo, String email) throws SQLException {
		String query = "DELETE FROM Utente_indirizzi WHERE id=? AND Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id_indirizzo);
		statement.setString(2, email);
		
		int errore = statement.executeUpdate();
		
		statement.close();
		
		return errore;
	}
	
}
