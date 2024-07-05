package com.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Comune;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;

@Repository 
public interface SegnalazioneRepository extends JpaRepository <Segnalazione,Long> {
	
	List<Segnalazione> findByData(LocalDate data);

}
