package it.alexandria.hibernate.control;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import it.alexandria.hibernate.model.Commento;
import it.alexandria.hibernate.model.Messaggio;
import it.alexandria.hibernate.model.Profilo;
import it.alexandria.hibernate.model.Risorsa;
import it.alexandria.hibernate.model.Vendita;


/**
 * Class used to perform CRUD operation on database with Hibernate API's
 */
public class App {
  @SuppressWarnings("unused")
  public static void main(String[] args) {

    App application = new App();

    /*
     * Save few objects with hibernate
     */
		/*
		 * String profiloId1 = application.saveProfilo("Username3","Sam", "Disilva",
		 * LocalDate.now(), new ArrayList<Risorsa>(), new ArrayList<Messaggio>(), new
		 * ArrayList<Messaggio>(), new ArrayList<Vendita>(), new ArrayList<Vendita>(),
		 * "io@mail.com", "330100", "nowhere");
		 * 
		 * String profiloId2 = application.saveProfilo("Username4","Sam", "Disilva",
		 * LocalDate.now(), new ArrayList<Risorsa>(), new ArrayList<Messaggio>(), new
		 * ArrayList<Messaggio>(), new ArrayList<Vendita>(), new ArrayList<Vendita>(),
		 * "io@mail.com", "330100", "nowhere");
		 */
    /*
     * public Messaggio(long id, Profilo mittente, Profilo destinatario, String testo, Date data) {
		super();
		this.id = id;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.testo = testo;
		this.data = data;
	}*/
    List<Profilo> profilos = application.getAllProfilos();
    application.saveMessaggio(profilos.get(0), profilos.get(1), "messaggio stupido", java.sql.Date.valueOf(LocalDate.now()));
    
    /*
     * Retrieve all saved objects
     */
    System.out.println("List of all persisted employees >>>");
    for (Profilo profilo : profilos) {
      System.out.println("Persisted Employee :" + profilos);
    }

    /*
     * Update an object
     */
    //application.updateProfilo("username1","tu@mail.com");

    /*
     * Deletes an object
     */
    //application.deleteProfilo(profiloId2);

    /*
     * Retrieve all saved objects
     */
    List<Profilo> remaingProfilos = application.getAllProfilos();
    System.out.println("List of all remained persisted employees >>>");
    for (Profilo profilo : remaingProfilos) {
      System.out.println("Persisted Employee :" + profilo);
    }
  }

  /**
   * This method saves a Employee object in database
   */
  public String saveProfilo(String username, String nome, String cognome, LocalDate data, List<Risorsa> libreria,
			List<Messaggio> messaggiInviati, List<Messaggio> messaggiRicevuti, List<Vendita> risorseAcquistate,
			List<Vendita> risorseVendute, String email, String numeroTel, String indirizzo) {
    Profilo profilo = new Profilo();
    profilo.setUsername(username);
    profilo.setNome(nome);
    profilo.setCognome(cognome);
    profilo.setData(java.sql.Date.valueOf(data));
    profilo.setLibreria(libreria);
    profilo.setMessaggiInviati(messaggiInviati);
    profilo.setMessaggiRicevuti(messaggiRicevuti);
    profilo.setRisorseAcquistate(risorseAcquistate);
    profilo.setRisorseVendute(risorseVendute);
    profilo.setEmail(email);
    profilo.setNumeroTel(numeroTel);
    profilo.setIndirizzo(indirizzo);

    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    String id = (String) session.save(profilo);
    session.getTransaction().commit();
    session.close();
    return id;
  }

	public long saveMessaggio(Profilo mittente, Profilo destinatario, String testo, Date data) {
		Messaggio messaggio = new Messaggio();
		messaggio.setData(data);
		messaggio.setDestinatario(destinatario);
		messaggio.setMittente(mittente);
		messaggio.setTesto(testo);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		long identifier = (Long) session.save(messaggio);
		session.getTransaction().commit();
		session.close();
		return identifier;
	}
  /**
   * This method returns list of all persisted Employee objects/tuples from database
   */
  public List<Profilo> getAllProfilos() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    @SuppressWarnings("unchecked")
    List<Profilo> profilos = (List<Profilo>) session.createQuery(
      "from Profilo s").list();

    session.getTransaction().commit();
    session.close();
    return profilos;
  }

  /**
  * This method updates a specific Employee object
  */
  public void updateProfilo(String username, String email) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    Profilo profilo = (Profilo) session.get(Profilo.class, username);
    profilo.setEmail(email);
    List<Risorsa> risorse= profilo.getLibreria();
    Risorsa risorsa=risorse.get(1);
    /*long id, Vendita vendita, String titolo, Categoria categoria, String autori, Date data, float prezzo,
			String descrizione, Profilo proprietario, List<Commento> commenti*/
    /*risorsa.setAutori("autori");
    risorsa.setTitolo("titolo");
    risorsa.setCategoria(Categoria.FISICA);
    risorsa.setData(java.sql.Date.valueOf(LocalDate.now()));
    risorsa.setPrezzo(10);
    risorsa.setDescrizione("Ciao ciao");*/
    Commento commento=new Commento();
    commento.setMittente(profilo);
    commento.setRisorsaConnessa(risorsa);
    commento.setData(java.sql.Date.valueOf(LocalDate.now()));
    commento.setTesto("Questa risorsa fa schifo");
    risorsa.getCommenti().add(commento);
    
    //session.update(employee);//No need to update manually as it will be updated automatically on transaction close.
    session.getTransaction().commit();
    session.close();
  }

  /**
   * This method deletes a specific Employee object
   */
  public void deleteProfilo(String id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    Profilo employee = (Profilo) session.get(Profilo.class, id);
    session.delete(employee);
    session.getTransaction().commit();
    session.close();
  }
}