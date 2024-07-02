package com.services.utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Utente;
import com.repositories.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void save(Utente utente) {
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		repository.save(utente);
	}
}
