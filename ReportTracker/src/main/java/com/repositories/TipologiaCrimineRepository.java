package com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.model.TipologiaCrimine;

@Repository 
public interface TipologiaCrimineRepository extends JpaRepository <TipologiaCrimine,Integer> {

}

