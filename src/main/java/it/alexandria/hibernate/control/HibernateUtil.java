package it.alexandria.hibernate.control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();
  private static PrintWriter writer=null;

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      return new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
  
  public static void printLog(String str) {
	  if(writer==null) {
		  try {
			writer = new PrintWriter(new FileWriter("C:\\Users\\lucas\\OneDrive\\Desktop\\alexandrialog.txt",true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	  DateTimeFormatter dfm=DateTimeFormatter.ofPattern("yyy/MM/dd hh:mm ");
	  writer.println(dfm.format(LocalDateTime.now())+str);
	  writer.flush();
  }
  
  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }
}