package it.alexandria.hibernate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CREDENZIALI")
public class Credenziali implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -973775316568843526L;
	@Id
	private String username;
	private String password;
	public Credenziali() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
