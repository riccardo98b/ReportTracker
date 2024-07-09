package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>{

	Optional<Utente> findByUsername(String username);
	
}
