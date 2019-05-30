package it.alexandria.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name="COMMENTO")
public class Commento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5700257516066054172L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMMENT_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RISORSA_ASSOCIATA")
	private Risorsa risorsaConnessa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MITTENTE_COMMENTO")
	private Profilo mittente;
	
	private String testo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Risorsa getRisorsaConnessa() {
		return risorsaConnessa;
	}

	public void setRisorsaConnessa(Risorsa risorsaConnessa) {
		this.risorsaConnessa = risorsaConnessa;
	}

	public Profilo getMittente() {
		return mittente;
	}

	public void setMittente(Profilo mittente) {
		this.mittente = mittente;
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

	public void setData(Date data) {
		this.data = data;
	}

	public Commento() {
		super();
	}
}
