package it.alexandria.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="MESSAGGIO")
public class Messaggio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8564612299963335790L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEX_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MITTENTE")
	private Profilo mittente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DESTINATARIO")
	private Profilo destinatario;
	
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profilo getMittente() {
		return mittente;
	}

	public void setMittente(Profilo mittente) {
		this.mittente = mittente;
	}

	public Profilo getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Profilo destinatario) {
		this.destinatario = destinatario;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date timestamp) {
		this.data = timestamp;
	}

	public Messaggio() {
		super();
	}
}
