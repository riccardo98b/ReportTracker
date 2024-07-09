package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.FasciaOraria;

@Repository
public interface FasciaOrariaRepository extends JpaRepository<FasciaOraria,Long> {
	
	Optional<FasciaOraria> findByNome(String nome);
	
}
