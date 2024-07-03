package com.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.Ruolo;
import com.model.Utente;


//Questa classe implementa UserDetails per fornire i dettagli dell'utente a Spring Security
public class DatabaseUserDetails implements UserDetails {
 private static final long serialVersionUID = 1L; // ID per la serializzazione

 private final Utente utente; // L'utente di cui stiamo gestendo i dettagli
 private final Set<GrantedAuthority> authorities; // Le autorità (ruoli) dell'utente

 // Costruttore che accetta un oggetto Utente
 public DatabaseUserDetails(Utente utente) {
     this.utente = utente;

     // Inizializza l'insieme delle autorità
     authorities = new HashSet<GrantedAuthority>();
     
     // Aggiunge i ruoli dell'utente alle autorità
     for (Ruolo ruolo : utente.getRuolo()) {
         authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
     }
 }

 @Override
 // Restituisce le autorità (ruoli) dell'utente
 public Collection<? extends GrantedAuthority> getAuthorities() {
     return authorities;
 }

 @Override
 // Restituisce la password dell'utente
 public String getPassword() {
     return this.utente.getPassword();
 }

 @Override
 // Restituisce lo username dell'utente
 public String getUsername() {
     return this.utente.getUsername();
 }

 @Override
 // Indica se l'account dell'utente è non scaduto (sempre vero in questo caso)
 public boolean isAccountNonExpired() {
     return true;
 }

 @Override
 // Indica se l'account dell'utente è non bloccato (sempre vero in questo caso)
 public boolean isAccountNonLocked() {
     return true;
 }

 @Override
 // Indica se le credenziali dell'utente sono non scadute (sempre vero in questo caso)
 public boolean isCredentialsNonExpired() {
     return true;
 }

 @Override
 // Indica se l'utente è abilitato (sempre vero in questo caso)
 public boolean isEnabled() {
     return true;
 }
}