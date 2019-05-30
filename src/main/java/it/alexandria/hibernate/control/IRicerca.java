package it.alexandria.hibernate.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.alexandria.hibernate.model.Risorsa;

public interface IRicerca {
	public List<Risorsa> ottieniRisorse(HttpServletRequest request, HttpServletResponse response);
}
