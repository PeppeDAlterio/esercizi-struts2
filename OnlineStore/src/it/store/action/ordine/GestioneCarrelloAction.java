package it.store.action.ordine;

import it.store.bean.catalogo.articoli.DatiArticoloBean;
import it.store.dto.Articolo;
import it.store.dto.Carrello;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class GestioneCarrelloAction extends ActionSupport implements Preparable {
	
	public int idArticolo = -1,
			   quantita_ordinata = 1;
	
	public int index = -1; //indice per individuare un articolo nella lista 
		
	private Articolo articolo; //articolo su cui eventulamente "lavorare"
		
	private Carrello carrello;
	
	@Override
	public void prepare() {
		ActionContext context = ActionContext.getContext();
		carrello = (Carrello)context.getSession().get("carrello");
		
		if(carrello==null) {
			carrello = new Carrello();
			context.getSession().put("carrello", carrello);
		}
		
	}
	
	
	public String aggiungiArticolo() {
		
		if(idArticolo>0) {
			
			DatiArticoloBean articoliBean = new DatiArticoloBean();
			
			articoliBean.setIdArticolo(idArticolo);
			
			this.articolo = articoliBean.getArticolo();
			
			if(articolo==null) { //articolo non trovato
				return INPUT;
			}
			
			//ok!
			
			//setto la quantità
			this.articolo.setQuantita_ordinata(quantita_ordinata);
			
			//controllo che l'articolo non sia già presente nel carrello
			int i=0, da_rimuovere = -1;
			for (Articolo art: carrello.getArticoli()) {
				if(art.getIdArticolo() == articolo.getIdArticolo()) {
					// se l'articolo è già presente, lo segno da rimuovere
					// per aggiungere il nuovo
					da_rimuovere = i;
				}
				i++;
			}
			
			if(da_rimuovere>-1) {
				carrello.getArticoli().remove(da_rimuovere);
			}
			
			//aggiungo l'articolo al carrello
			this.carrello.aggiungiArticolo(this.articolo);
						
			addActionMessage(getText("carrello.aggiugiArticolo_success"));
			return SUCCESS;
			
			
		} else {
			return INPUT;
		}
	}
	
	public String rimuoviArticolo() {
		if(index>-1) {
			
			try {
				carrello.getArticoli().remove(index);
			} catch (IndexOutOfBoundsException e) {
				addActionError(getText("carrello.rimuoviArticolo_notfound"));
				return ERROR;
			}
			
			addActionMessage(getText("carrello.rimuoviArticolo_success"));
			return SUCCESS;
			
		} else {
			
			addActionError(getText("carrello.rimuoviArticolo_notfound"));
			return INPUT;
		}
	}
	
	public String aggiornaQuantita() {
		if(index>-1 && quantita_ordinata>0) {
			
			try {
				articolo = carrello.getArticoli().get(index);
				articolo.setQuantita_ordinata(quantita_ordinata);
			} catch (IndexOutOfBoundsException e) {
				addActionError(getText("carrello.rimuoviArticolo_notfound"));
				return ERROR;
			}
			
			addActionMessage(getText("carrello.aggiornaArticolo_success"));
			return SUCCESS;
			
		} else {
			
			addActionError(getText("carrello.rimuoviArticolo_notfound"));
			return INPUT;
		}
	}

}
