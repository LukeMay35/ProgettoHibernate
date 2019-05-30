package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IGestioneRisorsa {
	public boolean modificaRisorsa(HttpServletRequest request, HttpServletResponse response);
	public void inserisciCommento(HttpServletRequest request, HttpServletResponse response);
	public void rimuoviCommento(HttpServletRequest request, HttpServletResponse reponse);
	public void mostraRisorsa(HttpServletRequest request, HttpServletResponse reponse);
}
