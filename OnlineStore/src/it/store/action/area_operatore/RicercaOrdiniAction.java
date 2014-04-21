package it.store.action.area_operatore;

import java.sql.SQLException;
import java.util.List;

import it.store.dto.Ordine;
import it.store.filtro.FiltroRicercaOrdini;
import it.store.service.OrdineService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class RicercaOrdiniAction extends ActionSupport implements ModelDriven<FiltroRicercaOrdini> {
	
	private FiltroRicercaOrdini filtro = new FiltroRicercaOrdini();
	
	public int page = 0;
	
	private List<Ordine> risultati;
	
	public String execute() {
		
		OrdineService ordineService;
		try {
			ordineService = new OrdineService();
			this.risultati = ordineService.ricercaOrdini(filtro, page);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(getText("errori.generico"));
		} catch (SQLException e) {
			e.printStackTrace();
			addActionError(getText("sql.generic"));
		} finally {
			ordineService = null;
			
			if(hasErrors()) {
				return ERROR;
			}
		}
		
		if(getRisultati().isEmpty()) {
			setRisultati(null);
		}
		
		return SUCCESS;
	}

	public List<Ordine> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<Ordine> risultati) {
		this.risultati = risultati;
	}

	@Override
	public FiltroRicercaOrdini getModel() {
		return filtro;
	}
	

}
