package com.services.utente;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Ruolo;
import com.model.Utente;
import com.repositories.RuoloRepository;
import com.repositories.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository repository;
	
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    public void save(Utente utente) {
        // Codifica la password
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        
        Ruolo ruolo = ruoloRepository.findByNome("USER").orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        Set<Ruolo> ruoli = new HashSet<>();
        ruoli.add(ruolo);
        utente.setRuolo(ruoli); 
        
        // Recupera il ruolo con ID 2 dal database
       /* Ruolo ruolo = ruoloRepository.findById(2L).orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
        ruolo.setNome("USER");
        utente.setRuolo(ruolo);*/

        // Salva l'utente nel database
        repository.save(utente);
    }
    
    public Utente getUtenteAutenticato() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Optional<Utente> utenteOptional = repository.findByUsername(username);
                if (utenteOptional.isPresent()) {
                    return utenteOptional.get();
                }
            }
        }
        return null;
    }
    
    public Utente findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));
    }
}
