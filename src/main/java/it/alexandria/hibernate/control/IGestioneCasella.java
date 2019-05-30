package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IGestioneCasella {
	public void inviaMessaggio(HttpServletRequest request, HttpServletResponse response);
	public void rimuoviMessaggio(HttpServletRequest request, HttpServletResponse response);
	public void mostraMessaggi(HttpServletRequest request, HttpServletResponse response);
}
