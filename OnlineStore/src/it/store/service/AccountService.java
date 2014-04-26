package it.store.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.store.dto.User;

public class AccountService extends DatabaseService {
	
	public AccountService() throws ClassNotFoundException, SQLException {
		super();
	}

	/*
	 * Metodo per inserire nel database un nuovo utente
	 */
	public int nuovoAccount(User userData) throws SQLException {
//TODO: Controlla funzionamento
		String query = "INSERT INTO Utente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, userData.email);
		statement.setString(2, userData.userId);
		statement.setString(3, userData.getPassword());
		statement.setString(4, userData.nome);
		statement.setString(5, userData.cognome);
		statement.setString(6, userData.getCodice_fiscale());
		statement.setString(7, userData.telefono_fisso);
		statement.setString(8, userData.telefono_mobile);
		statement.setString(9, userData.email_secondaria);
		statement.setInt(10, userData.getTipo());
		int error = statement.executeUpdate();
		statement.close();
		
		return error;
	}
	
	
	/*
	 * Metodo per effettuare il login
	 */
	public boolean accedi(User userData) throws SQLException {
//TODO: Verifica funzionamento (?)
		String query = "SELECT * FROM Utente WHERE email=? AND password=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, userData.email);
		statement.setString(2, userData.getPassword());
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			/* riempio il DTO con i dati utente letti dal database */
			userData.userId 			= result.getString("userid");
			userData.nome 				= result.getString("nome");
			userData.cognome 			= result.getString("cognome");
			userData.telefono_fisso 	= result.getString("telefono_fisso");
			userData.telefono_mobile 	= result.getString("telefono_mobile");
			userData.email_secondaria 	= result.getString("email_secondaria");
			userData.setCodice_fiscale(result.getString("codice_fiscale"));
			userData.setTipo(result.getInt("tipo"));
			
			/* rimuovo l'MD5 della password dal DTO */
			userData.setPassword(null);
			
			result.close();
			statement.close();
			
			return true;
		} else {
			result.close();
			statement.close();
			
			return false;
		}
	}
	
	/*
	 * Metodo per la modifica dei dati del profilo utente (password esclusa)
	 */
	public int modificaProfilo(User userData) throws SQLException {
//FIXME: Aggiungi tipo account!
		String query = "UPDATE Utente SET userid=?, nome=?, cognome=?, telefono_fisso=?, telefono_mobile=?, email_secondaria=? WHERE email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, userData.userId);
		statement.setString(2, userData.nome);
		statement.setString(3, userData.cognome);
		statement.setString(4, userData.telefono_fisso);
		statement.setString(5, userData.telefono_mobile);
		statement.setString(6, userData.email_secondaria);
		statement.setString(7, userData.email);
		int errore = statement.executeUpdate();
		
		statement.close();
		
		return errore;
	}
	
	/*
	 * Metodo per la modifica della password dell'account utente
	 */
	public int modificaPassword(String email, String vecchia_password, String nuova_password) throws SQLException {
		String query = "UPDATE Utente SET password=? WHERE email=? AND password=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nuova_password);
		statement.setString(2, email);
		statement.setString(3, vecchia_password);
		int errore = statement.executeUpdate();
		
		return errore;
	}

	public User getAccountByEmail(String email) throws SQLException {
		
		User userData = new User();
		
		String query = "SELECT * FROM Utente WHERE email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			riempi_dati_account(userData, result);
		} else {
			userData = null;
		}
		
		result.close();
		statement.close();
		
		return userData;
	}

	public User getAccountByUserId(String userId) throws SQLException {
		
		User userData = new User();
		
		String query = "SELECT * FROM Utente WHERE userId=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, userId);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			riempi_dati_account(userData, result);
		} else {
			userData = null;
		}
		
		result.close();
		statement.close();
		
		return userData;
	}
	
	public void modificaAccount(String email, User nuoviDati) throws SQLException {
		String query = "UPDATE Utente SET userId=?, nome=?, cognome=?, codice_fiscale=?, telefono_fisso=?, telefono_mobile=?, "
						+ "email_secondaria=?, tipo=? WHERE email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nuoviDati.userId);
		statement.setString(2, nuoviDati.nome);
		statement.setString(3, nuoviDati.cognome);
		statement.setString(4, nuoviDati.getCodice_fiscale());
		statement.setString(5, nuoviDati.telefono_fisso);
		statement.setString(6, nuoviDati.telefono_mobile);
		statement.setString(7, nuoviDati.email_secondaria);
		statement.setInt(8, nuoviDati.getTipo());
		statement.setString(9, email);
		
		statement.executeUpdate();
		
		statement.close();
	}
	
	private void riempi_dati_account(User userData, ResultSet result) throws SQLException {
		userData.email  			= result.getString("email");
		userData.userId				= result.getString("userId");
		userData.nome				= result.getString("nome");
		userData.cognome			= result.getString("cognome");
		userData.setCodice_fiscale(result.getString("codice_fiscale"));
		userData.telefono_fisso		= result.getString("telefono_fisso");
		userData.telefono_mobile 	= result.getString("telefono_mobile");
		userData.email_secondaria	= result.getString("email_secondaria");
		userData.setTipo(result.getInt("tipo"));
	}
}
