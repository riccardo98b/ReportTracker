package com.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.model.Utente;
import com.repositories.UtenteRepository;

//Questa classe fornisce i dettagli dell'utente dal database per Spring Security

public class DatabaseUserDetailsService implements UserDetailsService{
	
    // Inietta il repository per accedere ai dati degli utenti dal database
    private @Autowired UtenteRepository utenteRepository;

	@Override
	// Questo metodo viene chiamato da Spring Security per caricare 
	//i dettagli dell'utente tramite username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente per il suo username nel database

		 Optional<Utente> optionalUtente = utenteRepository.findByUsername(username);
	        // Se l'utente non viene trovato, lancia un'eccezione
            if (!optionalUtente.isPresent()) {
	            throw new UsernameNotFoundException("Utente non trovato.");
	        }
            // Se l'utente viene trovato, ottieni i suoi dati
            Utente utente = optionalUtente.get();
            
            // Restituisce un oggetto User di Spring Security con username,
            //password e autorità (ruoli)
	        return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(), getAuthorities(utente));
	    
	}
	
	
    // Questo metodo restituisce i ruoli o le autorità dell'utente
    private Collection<? extends GrantedAuthority> getAuthorities(Utente utente) {
    	
     // Per semplicità, a tutti gli utenti viene assegnato il ruolo "USER"
     return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
}
	

