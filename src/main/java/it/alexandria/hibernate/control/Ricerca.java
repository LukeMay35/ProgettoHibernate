package it.alexandria.hibernate.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import it.alexandria.hibernate.model.Risorsa;

public class Ricerca extends HttpServlet implements IRicerca{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590834714280558759L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		List<Risorsa> risorse=ottieniRisorse(request,response);
		if(risorse==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		request.getSession().setAttribute("risorseRicercate", risorse);
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);;
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Risorsa> ottieniRisorse(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			return null;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				return null;
			} 
		}
		String chiave=request.getParameter("chiave");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		if(chiave!=null) {
			chiave="%"+chiave+"%";
			HibernateUtil.printLog("Avvenuta ricerca da utente "+request.getSession().getAttribute("username")+" con chiave "+chiave);
			@SuppressWarnings("unchecked")
			List<Risorsa> risorseS=(List<Risorsa>)session.createQuery("FROM RISORSA WHERE RES_ID NOT IN (SELECT ven.risorsaVenduta.id FROM it.alexandria.hibernate.model.Vendita as ven) AND (TITOLO LIKE :CHIAVE OR CATEGORIA LIKE :CHIAVE OR AUTORI LIKE :CHIAVE OR DESCRIZIONE LIKE :CHIAVE)")
			.setParameter("CHIAVE", chiave)
			.getResultList();
			session.getTransaction().commit();
			session.close();
			return risorseS;
		}
		HibernateUtil.printLog("Avvenuta ricerca da utente "+request.getSession().getAttribute("username"));
		@SuppressWarnings("unchecked")
		List<Risorsa> risorse=(List<Risorsa>)session.createQuery("FROM RISORSA WHERE RES_ID NOT IN (SELECT ven.risorsaVenduta.id FROM it.alexandria.hibernate.model.Vendita as ven)")
		.getResultList();
		session.getTransaction().commit();
		session.close();
		return risorse;
	}

}
