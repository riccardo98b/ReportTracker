package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Ruolo;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
	Optional<Ruolo> findByNome(String nome);
}
