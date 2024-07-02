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

public class DatabaseUserDetailsService implements UserDetailsService{
	
	private @Autowired UtenteRepository utenteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<Utente> optionalUtente = utenteRepository.findByUsername(username);
	        if (!optionalUtente.isPresent()) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        Utente utente = optionalUtente.get();
	        return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(), getAuthorities(utente));
	    
	}
	
    private Collection<? extends GrantedAuthority> getAuthorities(Utente utente) {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
}
	

