package it.store.action.area_cliente;

import it.store.dto.Indirizzo;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ModificaIndirizzoOrdineAction extends ActionSupport implements ModelDriven<Indirizzo> {
	//TODO: COMPLETAMI
	private Indirizzo indirizzo = new Indirizzo();
	
	public void validate() {
		//TODO: COMPLETAMI
	}
	
	public String execute() {
		//TODO: COMPLETAMI
		return SUCCESS;
	}

	@Override
	public Indirizzo getModel() {
		return indirizzo;
	}
	
}
