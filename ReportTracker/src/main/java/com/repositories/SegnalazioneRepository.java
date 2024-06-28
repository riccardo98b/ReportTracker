package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Segnalazione;

@Repository 
public interface SegnalazioneRepository extends JpaRepository <Segnalazione,Long> {

}
