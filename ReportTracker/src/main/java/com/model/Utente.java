package com.model;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "utenti") // Il nome della tabella deve essere una stringa
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false, unique = true)
    private String username;

   @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cognome;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "ruolo_utente", joinColumns = {
                @JoinColumn(name = "utente_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ruolo_id", referencedColumnName = "id") 
            }
    )
    private Set<Ruolo> ruolo = new HashSet<>();
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Ruolo> getRuolo() {
		return ruolo;
	}

	public void setRuolo(Set<Ruolo> ruolo) {
		this.ruolo = ruolo;
	}



	
}