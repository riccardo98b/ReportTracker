package com.services.segnalazione;

import java.util.List;

import com.model.Segnalazione;

public interface SegnalazioneService {

	List<Segnalazione> findAll();
	
	Segnalazione findById(Long id) throws Exception;
	
	//RESTITUISCE UN NUOVO PUBLISCER
	Segnalazione save(Segnalazione segnalazione) throws Exception;

	//CANCELLA UN PUBLISHER DI ID SPECIFICATO
	void deleteById(Long id)throws Exception;
	
	//SOVRASCRIVE UN PUBLISHER DI ID SPECIFICATO
	Segnalazione updateById(Long id, Segnalazione segnalazione)throws Exception;
	
}
