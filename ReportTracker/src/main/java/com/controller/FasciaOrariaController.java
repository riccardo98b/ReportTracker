package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


import com.model.FasciaOraria;
import com.services.fascia_oraria.FasciaOrariaService;
@Controller
@RequestMapping("/fasciaorariaAD")

public class FasciaOrariaController {
	
	

	
		
	@Autowired
		private FasciaOrariaService service;
		
		// API per ottenere tutti i publisher
		// http://localhost:8080/api/v1/fasciaOraria    
		
		@GetMapping("/all")
	
		
		public ResponseEntity<?> findAll() throws Exception{
			List<FasciaOraria> fascOraria = service.findAll();
			if(fascOraria.isEmpty()) {
				return new ResponseEntity<>(fascOraria,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Fascia oraria non trovata",HttpStatus.OK);
				// QUI METTIAMO OK PERCHE NON CE UN ELENCO,QUINDI NON CONSIDERATO ERRORE
			}
		}
		
		// Visualizza una FasciaOraria tramite id
	    @GetMapping("find/{id}")
	    public ResponseEntity<?> findById(@PathVariable Long id) {
	        try {
	            FasciaOraria fasciaOraria = service.getFasciaOrariaById(id);
	            if (fasciaOraria != null) {
	                return new ResponseEntity<>(fasciaOraria, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Fascia oraria non trovata", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("Errore durante il recupero della fascia oraria", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
		
		@PostMapping("/creafascia")
	    public ResponseEntity<FasciaOraria> createFasciaOraria(@RequestBody FasciaOraria fasciaOraria) {
	        FasciaOraria nuovaFasciaOraria = service.addFasciaOraria(fasciaOraria);
	        return ResponseEntity.ok(nuovaFasciaOraria);
	    }
		
		// Cancella una FasciaOraria teamite un id
	    @DeleteMapping("/cancella/{id}")
	    public ResponseEntity<?> deleteFasciaOraria(@PathVariable Long id) {
	        try {
	            FasciaOraria existingFasciaOraria = service.getFasciaOrariaById(id);
	            if (existingFasciaOraria != null) {
	                service.removeFasciaOraria(id);
	                return new ResponseEntity<>("Fascia oraria eliminata con successo", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Fascia oraria non trovata", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("Errore durante l'eliminazione della fascia oraria", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	 // Aggiorna una FasciaOraria esistente
	    @PutMapping("/aggiorna/{id}")
	    public ResponseEntity<?> updateFasciaOraria(@PathVariable Long id, @RequestBody FasciaOraria updatedFasciaOraria) {
	        try {
	            FasciaOraria existingFasciaOraria = service.getFasciaOrariaById(id);
	            if (existingFasciaOraria != null) {
	                updatedFasciaOraria.setId(id); 
	                service.updateFasciaOraria(updatedFasciaOraria);
	                return new ResponseEntity<>(updatedFasciaOraria, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Fascia oraria non trovata", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("Errore durante l'aggiornamento della fascia oraria", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    
	        
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}
}
		
		
		

	
