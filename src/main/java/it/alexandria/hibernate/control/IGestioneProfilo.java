package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IGestioneProfilo {
	public void acquistoRisorsa(HttpServletRequest request, HttpServletResponse response);
	public void aggiungiAlCarrello(HttpServletRequest request, HttpServletResponse response);
	public boolean modificaProfilo(HttpServletRequest request, HttpServletResponse response);
	public void mostraProfilo(HttpServletRequest request, HttpServletResponse response);
}
