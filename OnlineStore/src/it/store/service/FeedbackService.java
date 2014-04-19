package it.store.service;

import it.store.dto.Feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService extends DatabaseService {

	public FeedbackService() throws ClassNotFoundException, SQLException {
		super();
	}
	
	public List<Feedback> getListaFbUtente(String utente) throws SQLException {
		List<Feedback> listaFeedback = new ArrayList<Feedback>();
		
		String query = "SELECT * FROM Feedback_ordine WHERE Utente_email=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, utente);
		ResultSet result = statement.executeQuery();
		
		Feedback tmp;
		while(result.next()) {
			tmp = new Feedback();
			
			riempi_feedback(tmp, result);
			
			listaFeedback.add(tmp);
			
		}
		
		if(listaFeedback.isEmpty()) {
			listaFeedback = null;
		}
		
		return listaFeedback;
	}
	
	private void riempi_feedback(Feedback fb, ResultSet result) throws SQLException {
		fb.setUtente_email(result.getString("Utente_email"));
		fb.setId_ordine(result.getInt("Ordine_id"));
		fb.setPunteggio(result.getInt("punteggio"));
		fb.setMessaggio(result.getString("messaggio"));
		fb.setStato(result.getInt("stato"));
		fb.setApprovato_da(result.getString("approvato_da"));
	}
	
	/*
	 * @return: false=ok | true=errore: ordine non trovato?
	 */
	public boolean inserisciFeedback(Feedback feedback, String utente) throws SQLException {
		
		String query = "UPDATE Feedback_ordine SET punteggio=?, messaggio=?, stato=-1 WHERE Utente_email=? AND Ordine_id=? AND stato=-2";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, feedback.getPunteggio());
		statement.setString(2, feedback.getMessaggio());
		statement.setString(3, utente);
		statement.setInt(4, feedback.getId_ordine());
		int righe = statement.executeUpdate();
		
		statement.close();
		
		if(righe==0) {
			return true;
		}
		
		return false;
	}

}
