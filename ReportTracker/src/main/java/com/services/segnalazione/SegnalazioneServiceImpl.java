package com.services.segnalazione;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;
import com.model.Utente;
import com.repositories.SegnalazioneRepository;

@Service
public class SegnalazioneServiceImpl implements SegnalazioneService{

	@Autowired
	private SegnalazioneRepository repository;
	
	
	
	@Override
	public List<Segnalazione> findAll() {
		return (List<Segnalazione>) repository.findAll();
	}

	@Override
	public Segnalazione findById(Long id) throws Exception {
		Optional<Segnalazione> optSegnalazione = repository.findById(id);
		if(optSegnalazione.isPresent()) {
			return optSegnalazione.get();			
		}
		
		throw new Exception("La segnalazione con l'id " + id + " non esiste");
	}

	@Override
	public Segnalazione save(Segnalazione segnalazione) throws Exception {
		Set<TipologiaCrimine> tC = segnalazione.getTipologiaCrimine();
		Comune comune = segnalazione.getComune();
		FasciaOraria fO = segnalazione.getFasciaOraria();
		Utente utente = segnalazione.getUtente();
		
		if(!tC.isEmpty() && comune != null && fO != null && utente != null){
			return repository.save(segnalazione);
		}
		throw new Exception("Uno dei valori è nullo");
	}

	@Override
	public void deleteById(Long id) throws Exception {
		if(repository.existsById(id)) {
			 repository.deleteById(id);
			
		}else { 
			throw new Exception("id non trovato"); 
		}
	}

	@Override
	public Segnalazione updateById(Long id, Segnalazione segnalazione) throws Exception {
		if(repository.existsById(id)) {
			return repository.save(segnalazione);
		}else {
			throw new Exception("id not found"); 
		}
	}

}
