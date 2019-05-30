package it.alexandria.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Profilo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1162709043630252617L;

	@Id
	@Column(name="USERNAME")
	private String username;
	
	private String nome;
	private String cognome;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="proprietario", fetch=FetchType.EAGER)
	private List<Risorsa> libreria;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="mittente")
	private List<Messaggio> messaggiInviati;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="destinatario")
	private List<Messaggio> messaggiRicevuti;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="acquirente")
	private List<Vendita> risorseAcquistate;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="venditore")
	private List<Vendita> risorseVendute;
	
	private String email;
	private String numeroTel;
	private String indirizzo;
	public Profilo() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
//	public List<Categoria> getInteressi() {
//		return interessi;
//	}
//	public void setInteressi(List<Categoria> interessi) {
//		this.interessi = interessi;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public List<Risorsa> getLibreria() {
		return libreria;
	}
	public void setLibreria(List<Risorsa> libreria) {
		this.libreria = libreria;
	}
	public List<Messaggio> getMessaggiInviati() {
		return messaggiInviati;
	}
	public void setMessaggiInviati(List<Messaggio> messaggiInviati) {
		this.messaggiInviati = messaggiInviati;
	}
	public List<Messaggio> getMessaggiRicevuti() {
		return messaggiRicevuti;
	}
	public void setMessaggiRicevuti(List<Messaggio> messaggiRicevuti) {
		this.messaggiRicevuti = messaggiRicevuti;
	}
	public List<Vendita> getRisorseAcquistate() {
		return risorseAcquistate;
	}
	public void setRisorseAcquistate(List<Vendita> risorseAcquistate) {
		this.risorseAcquistate = risorseAcquistate;
	}
	public List<Vendita> getRisorseVendute() {
		return risorseVendute;
	}
	public void setRisorseVendute(List<Vendita> risorseVendute) {
		this.risorseVendute = risorseVendute;
	}
	
	public boolean equals(Profilo p) {
		return this.username.equals(p.getUsername());
	}
}
