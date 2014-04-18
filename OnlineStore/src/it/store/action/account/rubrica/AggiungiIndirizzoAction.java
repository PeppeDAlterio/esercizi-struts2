package it.store.action.account.rubrica;

import java.sql.SQLException;

import it.store.dto.Indirizzo;
import it.store.service.RubricaService;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * Action che richiede l'aggiunta di un nuovo indirizzo in rubrica con i dati leggi da request.
 * Nel caso in cui è flaggato il campo 'modifica', vengono caricati i dati dell'indirizzo da modificare, passati alla view,
 * e successivamente richiesta la modifica del l'indirizzo in esame.
 */

@SuppressWarnings("serial")
public class AggiungiIndirizzoAction extends ActionSupport implements ModelDriven<Indirizzo> {
	
	Indirizzo indirizzo = new Indirizzo();
	
	/* flag che indica se si sta modifica un indirizzo, così da non eseguire il validate durante il caricamento dei dati dell'indirizzo da modificare */
	public String modifica = null;

	public void validate() {
		if(modifica==null) {
			if (StringUtils.isBlank(indirizzo.email)) {
				addFieldError("email", getText("errori.generico"));
			}
			
			if (StringUtils.isBlank(indirizzo.nome_cognome)) {
				addFieldError("nome_cognome", getText("fieldError.campo_vuoto"));
			}
			
			if (StringUtils.isBlank(indirizzo.indirizzo_1)) {
				addFieldError("indirizzo_1", getText("fieldError.campo_vuoto"));
			}
			
			if (StringUtils.isBlank(indirizzo.citta)) {
				addFieldError("citta", getText("fieldError.campo_vuoto"));
			}
			
			if (StringUtils.isBlank(indirizzo.provincia)) {
				addFieldError("provincia", getText("fieldError.campo_vuoto"));
			}
			
			if (StringUtils.isNumeric(indirizzo.cap)) {
				if(indirizzo.cap.length()!=5) {
					addFieldError("cap", getText("fieldError.formato_errato"));
				}
			}
			
			if (StringUtils.isBlank(indirizzo.paese)) {
				addFieldError("paese", getText("fieldError.campo_vuoto"));
			}
			
			if (!StringUtils.isBlank(indirizzo.telefono)) {
				if(indirizzo.telefono.length()<9 || !StringUtils.isNumeric(indirizzo.telefono)) {
					addFieldError("telefono", getText("fieldError.formato_errato"));
				}
			}
		}
	}
	
	public String aggiungiIndirizzo() {
//TODO: i18n !
		RubricaService rubricaService;
		try {
			rubricaService = new RubricaService();
			if (rubricaService.aggiungiModificaIndirizzo(indirizzo) == 0) {
				addActionError("Si e' verificato un errore.<br>"
								+ "Riprovare.");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError("Si e' verificato un errore.<br>"
					+ "Riprovare.");
		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate")) {
				addActionError("Si e' verificato un errore:<br>"
								+ "Indirizzo gia' presente in rubrica!");
			} else {
				addActionError("Si e' verificato un errore.<br>"
						+ "Riprovare.");
			}
		} finally {
			rubricaService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		/* se tutto è andato a buon fine, visualizzo la rubrica utente */
		
		addActionMessage("Indirizzo aggiunto/modificato con successo.");
		
		return SUCCESS;
	}

	@Override
	public Indirizzo getModel() {
		return indirizzo;
	}
	
}
