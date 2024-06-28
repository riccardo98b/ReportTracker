package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Comune;
@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long>{

}
