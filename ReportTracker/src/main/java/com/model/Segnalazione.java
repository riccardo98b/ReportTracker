package com.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Segnalazioni")
public class Segnalazione {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = true)
	private String foto_o_video;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utente_id", referencedColumnName = "id")
	private Utente utente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comune_id", referencedColumnName = "id")
	private Comune comune;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fascia_oraria_id", referencedColumnName = "id")
	private FasciaOraria fasciaOraria;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "tipologie_segnalazioni", joinColumns = {
				@JoinColumn(name = "tipologie_id", referencedColumnName = "id")
			},
			inverseJoinColumns = {
				@JoinColumn(name = "segnalazioni_id", referencedColumnName = "id") 
			}
	)
	private Set<TipologiaCrimine> tipologiaCrimine = new HashSet<>();

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getFoto_o_video() {
		return foto_o_video;
	}

	public void setFoto_o_video(String foto_o_video) {
		this.foto_o_video = foto_o_video;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Comune getComune() {
		return comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}

	public FasciaOraria getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(FasciaOraria fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	public Set<TipologiaCrimine> getTipologiaCrimine() {
		return tipologiaCrimine;
	}

	public void setTipologiaCrimine(Set<TipologiaCrimine> tipologiaCrimine) {
		this.tipologiaCrimine = tipologiaCrimine;
	}
	
	
}
