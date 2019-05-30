package it.alexandria.hibernate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/*
 *  QUESTA E' UNA CLASSE DI MAPPING TRA UTENTI E CATEGORIE
 */

@Entity(name="UCMAPPING")
public class UCMapping implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3410487213209973482L;
	@Id
	private String username;
	@Id
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	public UCMapping() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
