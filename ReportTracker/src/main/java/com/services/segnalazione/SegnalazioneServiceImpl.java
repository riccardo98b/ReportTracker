package com.services.segnalazione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;
import com.model.Utente;
import com.my_methods.MyMethods;
import com.repositories.SegnalazioneRepository;
import com.services.comune.ComuneService;
import com.services.fascia_oraria.FasciaOrariaService;
import com.services.tipologia_crimine.TipologiaCrimineService;
import com.services.utente.UtenteService;


@Service
public class SegnalazioneServiceImpl extends MyMethods implements SegnalazioneService{

	@Autowired
	private SegnalazioneRepository repository;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private ComuneService comuneService;
	
	@Autowired
	private FasciaOrariaService foService;
	
	@Autowired
	private TipologiaCrimineService tcService;
	
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
		Optional<Segnalazione> optS = repository.findById(id);
		if(optS.isPresent()) {
			Segnalazione existS = optS.get();
			existS.setDescrizione(segnalazione.getDescrizione());
			existS.setData(segnalazione.getData());
			existS.setFoto_o_video(segnalazione.getFoto_o_video());
			existS.setComune(segnalazione.getComune());
			existS.setFasciaOraria(segnalazione.getFasciaOraria());
			existS.setTipologiaCrimine(segnalazione.getTipologiaCrimine());
			existS.setUtente(segnalazione.getUtente());
			
			return repository.save(existS);
		}else {
			throw new Exception("id not found"); 
		}
	}
	
/*	@Override
	public Segnalazione updateById(Long id, Segnalazione segnalazione) throws Exception {
		if(repository.existsById(id)) {
			return repository.save(segnalazione);
		}else {
			throw new Exception("id not found"); 
		}
	}*/

	
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

	@Override
	public List<Segnalazione> findSegnalazioniByUtenteLoggato() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = null;

	    if (authentication.getPrincipal() instanceof UserDetails) {
	        username = ((UserDetails) authentication.getPrincipal()).getUsername();
	    } else {
	        username = authentication.getPrincipal().toString();
	    }

	    Utente utente = utenteService.findByUsername(username);
	    return repository.findByUtente(utente);
	}
	

	/*@Override
	public void saveCSV() throws Exception {
	    // commento il percorso 
	    // Samuele 
	    String file = "C:\\Users\\Samuele\\OneDrive\\Documenti\\testJava/Dati Segnalazioni DB - Foglio1.csv";

	    List<String> listFile = new ArrayList<>();
	    try {
	        listFile = lettura(file);

	        for (String s : listFile) {
	            String[] sup = s.split(",");
	            

	            Segnalazione segnalazione = new Segnalazione();
	             
	            segnalazione.setDescrizione(sup[0]);
	            
	            if (sup.length < 6) {
	            	System.out.println("Riga non valida: " + sup[3]);
	            	continue; // Salta la riga se non ha abbastanza colonne
	            }
	            
	            String dataSplit[] = sup[1].split("/");
	            
	            String mese = dataSplit[1], giorno = dataSplit[0];
	            
	            if(dataSplit[1].length() < 2) {
	            	mese = 0 + dataSplit[1];
	            } else if(Integer.parseInt(mese) >= 13){
	            	mese = "01";
	            }
	            	
	            if(dataSplit[0].length() < 2) {
	            	giorno = 0 + dataSplit[0];
	            }
	            
	            LocalDate date = LocalDate.parse(dataSplit[2] + "-" + mese + "-" + giorno);
	            segnalazione.setData(date);

	            Comune c = comuneService.findById(Long.parseLong(sup[2]));
	            segnalazione.setComune(c);
	            
	            FasciaOraria f = foService.getFasciaOrariaById(Long.parseLong(sup[3]));
	            segnalazione.setFasciaOraria(f);
	            
	            String foto;
	            if(sup[4].equals("") || sup[4] == null) {
	            	foto = null;
	            }else {
	            	foto = sup[4];
	            }
	            segnalazione.setFoto_o_video(foto);
	            
	            Utente u = utenteService.findById(Long.parseLong(sup[5]));
	            segnalazione.setUtente(u);
	            
	            Set<TipologiaCrimine> tc = new HashSet<>();
	            TipologiaCrimine t = tcService.findByNomeTipologiaCrimine(sup[6]);
	            tc.add(t);
	            segnalazione.setTipologiaCrimine(tc);
	            
	            
	            repository.save(segnalazione);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}*/
}
	
	
	
	
	
	
	
	
	


