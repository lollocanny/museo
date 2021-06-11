package it.uniroma3.siw.model;

import java.beans.Transient;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity

public class Opera {
	
	public Opera() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titolo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataPubblicazione;
	
	private String luogo;
	
	@Column(nullable = true, length = 64)
	private String immagine;
	
	private Boolean isLong;
	
	private String descrizione;
	
	@ManyToOne
	private Artista autore;
	
	@ManyToOne
	private Collezione collezione;
	
	@Transient
	public String getPathImmagine() {
		if (immagine == null) return null;
		
		return "/images/operas/" + immagine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(LocalDate dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Boolean getIsLong() {
		return isLong;
	}

	public void setIsLong(Boolean isLong) {
		this.isLong = isLong;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Artista getAutore() {
		return autore;
	}

	public void setAutore(Artista autore) {
		this.autore = autore;
	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((collezione == null) ? 0 : collezione.hashCode());
		result = prime * result + ((dataPubblicazione == null) ? 0 : dataPubblicazione.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((immagine == null) ? 0 : immagine.hashCode());
		result = prime * result + ((isLong == null) ? 0 : isLong.hashCode());
		result = prime * result + ((luogo == null) ? 0 : luogo.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opera other = (Opera) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (collezione == null) {
			if (other.collezione != null)
				return false;
		} else if (!collezione.equals(other.collezione))
			return false;
		if (dataPubblicazione == null) {
			if (other.dataPubblicazione != null)
				return false;
		} else if (!dataPubblicazione.equals(other.dataPubblicazione))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (immagine == null) {
			if (other.immagine != null)
				return false;
		} else if (!immagine.equals(other.immagine))
			return false;
		if (isLong == null) {
			if (other.isLong != null)
				return false;
		} else if (!isLong.equals(other.isLong))
			return false;
		if (luogo == null) {
			if (other.luogo != null)
				return false;
		} else if (!luogo.equals(other.luogo))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
	
	
}
