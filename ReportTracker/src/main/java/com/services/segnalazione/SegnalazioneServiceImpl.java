package com.services.segnalazione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;
import com.repositories.FasciaOrariaRepository;
import com.repositories.SegnalazioneRepository;
import com.repositories.TipologiaCrimineRepository;
import com.services.comune.ComuneServiceImpl;
import com.services.fascia_oraria.FasciaOrariaServiceImpl;
import com.services.tipologia_crimine.TipologiaCrimineServiceImpl;


@Service
public class SegnalazioneServiceImpl implements SegnalazioneService{

	@Autowired
	private SegnalazioneRepository repository;
	
	@Autowired
	private ComuneServiceImpl comuneServiceImpl;
	
	@Autowired
	private FasciaOrariaServiceImpl fasciaOrariaServiceImpl;
	
	@Autowired
	private TipologiaCrimineServiceImpl tipologiaCrimineServiceImpl;
	
	
	
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

	/*@Override
	public Segnalazione save(Segnalazione segnalazione) throws Exception {
	    // Esempio di validazione dei campi obbligatori
	    if (segnalazione.getTipologiaCrimine() == null || segnalazione.getTipologiaCrimine().isEmpty() || 
	        segnalazione.getComune() == null || segnalazione.getFasciaOraria() == null) {
	        throw new IllegalArgumentException("Uno dei valori è nullo");
	    }

	    // Recupera il comune dal nome
	    Comune comune = comuneServiceImpl.findByNomeComune(segnalazione.getComune().getNome());
	    if (comune == null) {
	        throw new IllegalArgumentException("Comune non trovato con nome: " + segnalazione.getComune().getNome());
	    }
	    segnalazione.setComune(comune);

	    // Recupera la fascia oraria dal nome
	    FasciaOraria fasciaOraria = fasciaOrariaServiceImpl.findByNomeFasciaOraria(segnalazione.getFasciaOraria().getNome());
	    if (fasciaOraria == null) {
	        throw new IllegalArgumentException("Fascia oraria non trovata con nome: " + segnalazione.getFasciaOraria().getNome());
	    }
	    segnalazione.setFasciaOraria(fasciaOraria);

	    // Recupera le tipologie di crimine dai nomi
	    Set<TipologiaCrimine> tipologieCrimine = new HashSet<>();
	    for (TipologiaCrimine tc : segnalazione.getTipologiaCrimine()) {
	        TipologiaCrimine tipologiaCrimine = tipologiaCrimineServiceImpl.findByNomeTipologiaCrimine(tc.getNome());
	        if (tipologiaCrimine == null) {
	            throw new IllegalArgumentException("Tipologia crimine non trovata con nome: " + tc.getNome());
	        }
	        tipologieCrimine.add(tipologiaCrimine);
	    }
	    segnalazione.setTipologiaCrimine(tipologieCrimine);

	    // Salva la segnalazione nel repository
	    return repository.save(segnalazione);
	}*/
	
	@Override
	public Segnalazione save(Segnalazione segnalazione) throws Exception {
		try {
			return repository.save(segnalazione);
			
		}catch(Exception e)	{
				throw new Exception("Uno dei valori è nullo");
		}		
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
	
	
	
	
	
	
	
	
	


