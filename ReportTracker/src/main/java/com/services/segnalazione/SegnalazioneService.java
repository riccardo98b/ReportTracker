package com.services.segnalazione;

import java.util.List;

import com.model.Segnalazione;

public interface SegnalazioneService {
	
    // LISTA DI SEGNALAZIONI
	List<Segnalazione> findAll();
	
	// TROVA UNA SEGNALAZIONE PER ID
	Segnalazione findById(Long id) throws Exception;
	
	//RESTITUISCE UNA NUOVA SEGNAZIONE
	Segnalazione save(Segnalazione segnalazione) throws Exception;

	//CANCELLA UNA SEGNALAZIONE DI ID SPECIFICATO
	void deleteById(Long id)throws Exception;
	
	//SOVRASCRIVE UNA SEGNALAZIONE DI ID SPECIFICATO
	Segnalazione updateById(Long id, Segnalazione segnalazione)throws Exception;

	
}
