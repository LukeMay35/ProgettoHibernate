package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IGestioneLibreria {
	public boolean inserisciRisorsa(HttpServletRequest request, HttpServletResponse response); 
	public boolean rimuoviRisorsa(HttpServletRequest request, HttpServletResponse response); 
	public boolean mostraLibreria(HttpServletRequest request, HttpServletResponse response);
}
