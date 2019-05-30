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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="VENDITA")
public class Vendita implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6867289775775430326L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VENDITA_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACQUIRENTE")
	private Profilo acquirente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="VENDITORE")
	private Profilo venditore;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RIS_VENDUTA")
	private Risorsa risorsaVenduta ;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profilo getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(Profilo acquirente) {
		this.acquirente = acquirente;
	}

	public Profilo getVenditore() {
		return venditore;
	}

	public void setVenditore(Profilo venditore) {
		this.venditore = venditore;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date timestamp) {
		this.data = timestamp;
	}

	public Vendita() {
		super();
	}

	public Risorsa getRisorsaVenduta() {
		return risorsaVenduta;
	}

	public void setRisorsaVenduta(Risorsa risorsaVenduta) {
		this.risorsaVenduta = risorsaVenduta;
	}
}
