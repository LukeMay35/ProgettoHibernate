package it.alexandria.hibernate.control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import it.alexandria.hibernate.model.Categoria;
import it.alexandria.hibernate.model.Commento;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;

@SuppressWarnings("restriction")
public class GestioneLibreria extends HttpServlet implements IGestioneLibreria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3174630412508970267L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String tipo=request.getParameter("type");
		if(tipo==null) {
			mostraLibreria(request,response);
		}else if(tipo.equals("inserisci")) {
			inserisciRisorsa(request,response);
		}else if(tipo.equals("rimuovi")) {
			rimuoviRisorsa(request,response);
		}	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String line;
		String sourceData="";
		String name=request.getParameter("name");
		while((line=request.getReader().readLine())!=null) {
			sourceData+=line;
		}
		System.out.println("Qualunque cosa!");
		// tokenize the data
		String[] parts = sourceData.split(",");
		String imageString = parts[1];
		System.out.println(imageString);
		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
			System.out.println("qui ok");
			// write the image to a file
			File outputfile = new File("C:\\Users\\lucas\\OneDrive\\Desktop\\apache-tomcat-9.0.8\\webapps\\alexandria\\images\\"+name);
			System.out.println(ImageIO.write(image, "png", outputfile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean inserisciRisorsa(HttpServletRequest request, HttpServletResponse response) {
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
		HibernateUtil.printLog(request.getSession().getAttribute("username")+" richiede di inserire una risorsa");
		Risorsa risorsa = new Risorsa();
		risorsa.setAutori(request.getParameter("authors"));
		risorsa.setCategoria(Categoria.valueOf(request.getParameter("interest")));
		risorsa.setCommenti(new ArrayList<Commento>());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date data = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("releaseDate"), dtf));
		risorsa.setData(data);
		
		risorsa.setPrezzo(Float.parseFloat(request.getParameter("price")));
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Profilo profilo = (Profilo) session.get(Profilo.class, (String) request.getSession().getAttribute("username"));
		
		risorsa.setProprietario(profilo);
		risorsa.setTitolo(request.getParameter("title"));
		risorsa.setDescrizione(request.getParameter("description"));
		risorsa.setUrl(request.getParameter("image"));
		
		long id=(long)session.save(risorsa);
		HibernateUtil.printLog(request.getSession().getAttribute("username")+":inserimento avvenuto con successo. ID risorsa:"+id);
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

	public boolean rimuoviRisorsa(HttpServletRequest request, HttpServletResponse response) {
		String username=(String) request.getSession().getAttribute("username");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Profilo profilo = (Profilo) session.get(Profilo.class, username);
		Risorsa risorsa = (Risorsa) session.get(Risorsa.class, Long.parseLong(request.getParameter("id")));
		List<Risorsa> libreria=profilo.getLibreria();
		libreria.remove(risorsa);
		
		session.save(profilo);
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

	public boolean mostraLibreria(HttpServletRequest request, HttpServletResponse response) {
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
		
		String username=(String) request.getSession().getAttribute("username");
		HibernateUtil.printLog(request.getSession().getAttribute("username")+" richiede di mostrare la sua libreria");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Risorsa> risorse = (List<Risorsa>) session.createQuery("FROM RISORSA WHERE RES_OWNER = :RES AND RES_ID NOT IN (SELECT ven.risorsaVenduta.id FROM it.alexandria.hibernate.model.Vendita as ven)").setParameter("RES", username).getResultList();
		
		request.getSession().setAttribute("libreria",risorse);
		session.getTransaction().commit();
		session.close();
		
		try {
			request.getRequestDispatcher("library.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
