package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Messaggio;
import it.alexandria.hibernate.model.Profilo;

public class GestioneCasella extends HttpServlet implements IGestioneCasella{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4533644379994653773L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String tipo=request.getParameter("type");
		if(tipo==null) {
			mostraMessaggi(request,response);
		}else if(tipo.contentEquals("show_contact")) {
			request.getSession().setAttribute("first",request.getParameter("mittente"));
			try {
				response.sendRedirect("messages.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(tipo.contentEquals("invia_messaggio")) {
			inviaMessaggio(request,response);
		}else if(tipo.contentEquals("contatta_venditore")) {
			contattaVenditore(request,response);
		}
	}

	private void contattaVenditore(HttpServletRequest request, HttpServletResponse response) {
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
		String mittente=(String)request.getSession().getAttribute("mittente");
		String destinatario=(String)request.getSession().getAttribute("destinatario");
		Date data=java.sql.Timestamp.valueOf(LocalDateTime.now());
		String testo=mittente+" vuole iniziare una conversazione";
		HibernateUtil.printLog(mittente+" vuole contattare"+destinatario);
		Messaggio messaggio=new Messaggio();
		Profilo mitt=(Profilo) session.get(Profilo.class, mittente);
		Profilo dest=(Profilo) session.get(Profilo.class, destinatario);
		if(mitt.getMessaggiInviati().stream().filter(x->{return x.getTesto().equals(testo)&&x.getDestinatario().equals(dest);}).count()>0) {
			session.getTransaction().commit();
			session.close();
			mostraMessaggi(request,response);
			return;
		}
		
		messaggio.setMittente(mitt);
		messaggio.setDestinatario(dest);
		messaggio.setData(data);
		messaggio.setTesto(testo);
		
		long id=(long)session.save(messaggio);
		HibernateUtil.printLog(request.getSession().getAttribute("username")+" invia messaggio con ID "+id);
		session.getTransaction().commit();
		session.close();
		mostraMessaggi(request,response);
	}

	public void inviaMessaggio(HttpServletRequest request, HttpServletResponse response) {
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
		
		String destinatario=request.getParameter("destinatario");
		String username=(String)request.getSession().getAttribute("username");
		String testo=request.getParameter("testo");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profilo dest=(Profilo) session.get(Profilo.class, destinatario);
		Profilo mitt=(Profilo) session.get(Profilo.class, username);
		Messaggio messaggio = new Messaggio();
		messaggio.setData(java.sql.Timestamp.valueOf(LocalDateTime.now()));
		messaggio.setTesto(testo);
		messaggio.setMittente(mitt);
		messaggio.setDestinatario(dest);
		@SuppressWarnings("unchecked")
		List<Messaggio> messaggi=(List<Messaggio>)request.getSession().getAttribute("messaggi");
		messaggi.add(messaggio);
		request.getSession().setAttribute("messaggi", messaggi);
		long id=(long)session.save(messaggio);
		HibernateUtil.printLog(request.getSession().getAttribute("username")+" spedisce messaggio a "+destinatario+" con ID "+id);
		session.close();
		try {
			response.sendRedirect("messages.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rimuoviMessaggio(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void mostraMessaggi(HttpServletRequest request, HttpServletResponse response) {
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
		
		String username=(String)request.getSession().getAttribute("username");
		HibernateUtil.printLog(request.getSession().getAttribute("username")+" richiede di modificare la sua casella messaggi");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Messaggio> messaggi = (List<Messaggio>) session.createQuery("FROM MESSAGGIO WHERE MITTENTE= :USERNAME OR DESTINATARIO= :USERNAME").setParameter("USERNAME", username).getResultList();
		Set<String> personeUniche=new HashSet<String>();
		for(Messaggio m:messaggi) {
			if(!m.getMittente().getUsername().equals(username)&&m.getDestinatario().getUsername().equals(username)) {
				request.getSession().setAttribute("first",m.getMittente().getUsername());
				personeUniche.add(m.getMittente().getUsername());
			}else if(m.getMittente().getUsername().equals(username)&&!m.getDestinatario().getUsername().equals(username)) {
				request.getSession().setAttribute("first",m.getDestinatario().getUsername());
				personeUniche.add(m.getDestinatario().getUsername());
			}
		}
		session.getTransaction().commit();
		session.close();
		request.getSession().setAttribute("personeUniche", personeUniche);
		request.getSession().setAttribute("messaggi", messaggi);
		try {
			request.getRequestDispatcher("messages.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
