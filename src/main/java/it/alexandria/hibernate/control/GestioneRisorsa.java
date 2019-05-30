package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Commento;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;

public class GestioneRisorsa extends HttpServlet implements IGestioneRisorsa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5062957451523459725L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String tipo=request.getParameter("type");
		if(tipo==null) {
			mostraRisorsa(request,response);
		}else if(tipo.equals("modifica")) {
			modificaRisorsa(request,response);
		}else if(tipo.contentEquals("mostra")) {
			mostraRisorsa(request,response);
		}else if(tipo.equals("inserisciCommento")) {
			inserisciCommento(request,response);
		}
	}

	public boolean modificaRisorsa(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return false;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			} 
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		String prezzo = request.getParameter("price");
		String descrizione = request.getParameter("description");
		HibernateUtil.printLog("Modiifica risorsa con id "+risorsa.getId());
		if(!prezzo.equals("")) {
			risorsa.setPrezzo(Float.parseFloat(prezzo));
		}
		if(!descrizione.equals("")) {
			risorsa.setDescrizione(descrizione);
		}
		
		session.save(risorsa);
		session.getTransaction().commit();
		session.close();
		
		try {
			response.sendRedirect("library");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	public void inserisciCommento(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
		Date data=java.sql.Timestamp.valueOf(LocalDateTime.now());
		String username=request.getParameter("username");
		String testo=request.getParameter("testo");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profilo profilo = (Profilo) session.get(Profilo.class, username);
		Risorsa risorsa=(Risorsa)request.getSession().getAttribute("risorsa");
		
		Commento commento = new Commento();
		commento.setData(data);
		commento.setMittente(profilo);
		commento.setRisorsaConnessa(risorsa);
		commento.setTesto(testo);
		
		long id=(long)session.save(commento);
		HibernateUtil.printLog("Inserimento commento con id "+id+" da parte di "+username+" su risorsa "+ risorsa.getId());
		session.getTransaction().commit();
		session.close();
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		risorsa = (Risorsa) session.get(Risorsa.class, risorsa.getId());
		request.getSession().setAttribute("risorsa", risorsa);
		session.getTransaction().commit();
		session.close();
		
		try {
			request.getRequestDispatcher("single.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rimuoviCommento(HttpServletRequest request, HttpServletResponse reponse) {
		// TODO Auto-generated method stub
		
	}

	public void mostraRisorsa(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession()==null) {
			try {
				response.sendRedirect("login.html");
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			if (request.getSession().getAttribute("username") == null) {
				try {
					response.sendRedirect("login.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} 
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		HibernateUtil.printLog("Avviato procedura mostra su risorsa "+risorsa.getId());
		request.getSession().setAttribute("risorsa", risorsa);
		session.getTransaction().commit();
		session.close();
		try {
			response.sendRedirect("single.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
