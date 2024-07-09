package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.TipologiaCrimine;

@Repository 
public interface TipologiaCrimineRepository extends JpaRepository <TipologiaCrimine,Integer> {

	Optional<TipologiaCrimine> findByNome(String nome);
	
}

