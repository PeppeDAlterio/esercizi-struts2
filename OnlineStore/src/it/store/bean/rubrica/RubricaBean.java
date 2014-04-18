package it.store.bean.rubrica;

import it.store.dto.Indirizzo;
import it.store.service.RubricaService;

import java.sql.SQLException;
import java.util.List;

public class RubricaBean {
	
	private String utente;
	
	private List<Indirizzo> indirizzi;

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public List<Indirizzo> getIndirizzi() {
		
		RubricaService rubricaService;
		try {
			rubricaService = new RubricaService();
			indirizzi = rubricaService.getRubricaUtente(utente);
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			//FIXME:
			/*addActionError("Si e' verificato un errore.<br>"
							+ "Riprovare.");*/
		} finally {
			rubricaService = null;
			
			/*if(hasErrors()) {
				return ERROR;
			}*/
		}
		
		return indirizzi;
	}

	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

}
