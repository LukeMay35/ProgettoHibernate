package it.alexandria.hibernate.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ILogin {
	public boolean verificaCredenziali(String username, String password);
	public void registra(HttpServletRequest request, HttpServletResponse response);
	public void cambiaPassword(HttpServletRequest request, HttpServletResponse response);
	public void logout(HttpServletRequest request);
}
