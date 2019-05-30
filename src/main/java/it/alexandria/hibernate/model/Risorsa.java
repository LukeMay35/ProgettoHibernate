package it.alexandria.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name="RISORSA")
public class Risorsa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 894276491656218468L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RES_ID")
	private long id;
	
	private String titolo;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private String autori;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private float prezzo;
	private String descrizione;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RES_OWNER")
	private Profilo proprietario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="risorsaConnessa", fetch=FetchType.EAGER)
	private List<Commento> commenti;
	
	private String url;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAutori() {
		return autori;
	}

	public void setAutori(String autori) {
		this.autori = autori;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Profilo getProprietario() {
		return proprietario;
	}

	public void setProprietario(Profilo proprietario) {
		this.proprietario = proprietario;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	public Risorsa() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String urlImmagine) {
		this.url = urlImmagine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autori == null) ? 0 : autori.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((commenti == null) ? 0 : commenti.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(prezzo);
		result = prime * result + ((proprietario == null) ? 0 : proprietario.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return this.id==((Risorsa)obj).id;
	}
	
}
