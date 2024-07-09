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

 public DatabaseUserDetails(Utente utente) {
     this.utente = utente;

     // Inizializza l'insieme delle autorità
     authorities = new HashSet<GrantedAuthority>();
		for (Ruolo role : utente.getRuolo()) {
			authorities.add(new SimpleGrantedAuthority(role.getNome()));
		}
 }

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
     return authorities;
 }

 @Override
 public String getPassword() {
     return utente.getPassword();
 }

 @Override
 public String getUsername() {
     return utente.getUsername();
 }

 @Override
 public boolean isAccountNonExpired() {
     return true;
 }

 @Override
 public boolean isAccountNonLocked() {
     return true;
 }

 @Override
 public boolean isCredentialsNonExpired() {
     return true;
 }

 @Override
 public boolean isEnabled() {
     return true;
 }
}