package it.store.bean.rubrica;

import java.sql.SQLException;

import it.store.service.RubricaService;

/*
 * La bean, sotto classe del DTO indirizzo, legge i dati dell'indirizzo associato all'id passato in input (id_indirizzo)
 */
public class IndirizzoBean extends it.store.dto.Indirizzo {
		
	private String utente;

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public int getId_indirizzo() {
		return id_indirizzo;
	}
	
	public void setId_indirizzo(int id_indirizzo) {	
		
		this.id_indirizzo = id_indirizzo;
		
		boolean valido = false;
				
		RubricaService rubricaService;
		try {
			rubricaService = new RubricaService();
			valido = rubricaService.getIndirizzoById(this, utente);
			/*if(indirizzo==null) {
				addActionError("Si e' verificato un errore.");
			}*/
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			/*addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");*/
		} finally {
			rubricaService = null;
			
			/*if(hasErrors()) {
				return ERROR;
			}*/
		}
		
		if(valido==false) {
			this.id_indirizzo=-1;
		}
	}
	
}
