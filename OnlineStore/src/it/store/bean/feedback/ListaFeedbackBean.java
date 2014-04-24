package it.store.bean.feedback;

import it.store.dto.Feedback;
import it.store.service.FeedbackService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaFeedbackBean {

	private int page = 0;
	
	private int stato = -1,
				totale_pagine = 0;
	
	private List<Feedback> listaFeedback = new ArrayList<Feedback>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		System.out.println("OMG"+page);
		this.page = page;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public int getTotale_pagine() {
		return totale_pagine;
	}

	public void setTotale_pagine(int totale_pagine) {
		this.totale_pagine = totale_pagine;
	}

	public List<Feedback> getListaFeedback() {
		
		FeedbackService feedbackService;
		try {
			feedbackService = new FeedbackService();
			this.setTotale_pagine(feedbackService.getListaFeedback(getPage(), getStato(), listaFeedback));
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		} finally {
			feedbackService = null;
		}
		
		return listaFeedback;
	}

	public void setListaFeedback(List<Feedback> listaFeedback) {
		this.listaFeedback = listaFeedback;
	}
	
	
}
