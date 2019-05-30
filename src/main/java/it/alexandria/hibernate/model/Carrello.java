package it.alexandria.hibernate.model;

import java.util.List;

public class Carrello {
	private List<Risorsa> risorseSelezionate;

	public List<Risorsa> getRisorseSelezionate() {
		return risorseSelezionate;
	}

	public void setRisorseSelezionate(List<Risorsa> risorseSelezionate) {
		this.risorseSelezionate = risorseSelezionate;
	}

	public Carrello() {
		super();
	}
}
