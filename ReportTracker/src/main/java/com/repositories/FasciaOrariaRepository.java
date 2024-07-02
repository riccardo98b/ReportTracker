package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.FasciaOraria;

@Repository
public interface FasciaOrariaRepository extends JpaRepository<FasciaOraria,Long> {
	

}
