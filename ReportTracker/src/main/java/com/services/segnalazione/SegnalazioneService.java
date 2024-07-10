package com.services.segnalazione;

import java.time.LocalDate;
import java.util.List;

import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;

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
	
	//RESTITUISCE LA LISTA DELLE SEGNALAZIONI DELLA DATA SPECIFICA
	List<Segnalazione> findByData(LocalDate data)throws Exception;
	
	//RESTITUISCE LA LISTA DELLE SEGNALAZIONI DEL COMUNE SPECIFICATO
	List<Segnalazione> findByComune(String comune)throws Exception;
	
	//RESTITUISCE LA LISTA DELLE SEGNALAZIONI DELLA TIPOLOGIA DI CRIMINE SPECIFICATA
	List<Segnalazione> findByTipologiaCrimine(String tipologiacrimine)throws Exception;

	List<Segnalazione> findSegnalazioniByUtenteLoggato();
}
