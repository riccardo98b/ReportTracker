package com.restcontroller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Comune;
import com.model.FasciaOraria;
import com.model.Segnalazione;
import com.model.TipologiaCrimine;
import com.model.Utente;
import com.services.comune.ComuneServiceImpl;
import com.services.fascia_oraria.FasciaOrariaServiceImpl;
import com.services.segnalazione.SegnalazioneServiceImpl;
import com.services.tipologia_crimine.TipologiaCrimineServiceImpl;
import com.services.utente.UtenteService;

@Controller 
@RequestMapping("/segnalazioni")
public class SegnalazioniRestController {

	@Autowired
	private SegnalazioneServiceImpl service;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private ComuneServiceImpl comuneServiceImpl;
	
	@Autowired
	private FasciaOrariaServiceImpl fasciaOrariaServiceImpl;
	
	@Autowired
	private TipologiaCrimineServiceImpl tipologiaCrimineServiceImpl;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Segnalazione> segnalazione = service.findAll();
		
		if(!segnalazione.isEmpty()){
			return new ResponseEntity<>(segnalazione, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No author found", HttpStatus.OK);
		}
	}
	
	@PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Segnalazione segnalazione){
        try {
        	 // Ottieni l'utente autenticato
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	String username = authentication.getName(); // Ottieni il nome utente
        	Utente utente = utenteService.findByUsername(username);
        	
            Comune c = new Comune();
            c = comuneServiceImpl.findByNomeComune(segnalazione.getComune().getNome());
            if(c == null) {
            	throw new IllegalArgumentException("Comune non trovato con nome: " + segnalazione.getComune().getNome());
            }
            FasciaOraria f = fasciaOrariaServiceImpl.findByNomeFasciaOraria(segnalazione.getFasciaOraria().getNome());
            if(f == null) {
            	throw new IllegalArgumentException("Fascia oraria non trovata con nome: " + segnalazione.getFasciaOraria().getNome());
            }
            
           Set<TipologiaCrimine> tcSet = new HashSet<>();
            for(TipologiaCrimine n: segnalazione.getTipologiaCrimine()) {
            	TipologiaCrimine t = tipologiaCrimineServiceImpl.findByNomeTipologiaCrimine(n.getNome());
            	if(t == null) {
            		throw new IllegalArgumentException("Tipologia crimine non trovata con nome: " + n.getNome());
            	}
            	tcSet.add(t);
            }
            
            segnalazione.setComune(c);
            segnalazione.setFasciaOraria(f);
            segnalazione.setTipologiaCrimine(tcSet);
            
            // Ora puoi fare qualcosa con il nome utente, ad esempio salvarlo nella segnalazione
            segnalazione.setUtente(utente); // Supponendo che tu voglia settare il nome utente nella segnalazione
            
            return new ResponseEntity<>(service.save(segnalazione), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
	
	@PutMapping("/update/segnalazione/{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Segnalazione segnalazione){
		try {
			return new ResponseEntity<>(service.updateById(id, segnalazione), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/segnalazione/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		try {
			service.deleteById(id);
			return new ResponseEntity<>("la segnalazione è stata eliminata", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/by-data/{data}")/*http://localhost:8080/segnalazioni/by-data/ */
	public ResponseEntity<?> findByData(@PathVariable LocalDate data) {
		try {
			return new ResponseEntity<>(service.findByData(data),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/by-comune/{comune}")
	public ResponseEntity<?> findByComune(@PathVariable String comune) {
		try {
			return new ResponseEntity<>(service.findByComune(comune),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/by-tipologiacrimine/{tipologiacrimine}")
	public ResponseEntity<?> findByTipologiaCrimine(@PathVariable String tipologiacrimine) {
		try {
			return new ResponseEntity<>(service.findByTipologiaCrimine(tipologiacrimine),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
