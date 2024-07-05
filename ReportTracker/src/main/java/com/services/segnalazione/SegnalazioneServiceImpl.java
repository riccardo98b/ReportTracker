package com.services.segnalazione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;
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
		
		if(!tC.isEmpty() && comune != null && fO != null){
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

	
	@Override
	public List<Segnalazione> findByData(LocalDate data) throws Exception {
	    List<Segnalazione> segnalazioni = repository.findByData(data);

	    if (!segnalazioni.isEmpty()) {
	        return segnalazioni;
	    } else {
	        throw new Exception("Non ci sono segnalazioni in data: " + data);
	    }
	}
	
	
	@Override
	public List<Segnalazione> findByComune(String comune) throws Exception{
		List<Segnalazione> all=repository.findAll();
		List<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		for(Segnalazione s:all) {
			if(s.getComune().getNome().equalsIgnoreCase(comune)) {
				segnalazioni.add(s);
			}
		}
		if(!segnalazioni.isEmpty()) {
			return segnalazioni;
		}
		throw new Exception("Non ci sono segnalazioni nel comune: " + comune);
	}
	
	
	@Override
	public List<Segnalazione> findByTipologiaCrimine(String tipologiacrimine) throws Exception{
		List<Segnalazione> all=repository.findAll();
		List<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		for(Segnalazione s:all) {
			boolean contiene=false;
			for(TipologiaCrimine t:s.getTipologiaCrimine()) {
				if(t.getNome().equals(tipologiacrimine)) {
					contiene=true;
				}
			}
			if(contiene) {
				segnalazioni.add(s);
			}
		}
		if(!segnalazioni.isEmpty()) {
			return segnalazioni;
		}
		throw new Exception("Non ci sono segnalazioni di tipologia di crimine: " + tipologiacrimine);
	}
	
	
}
	
	
	
	
	
	
	
	
	


