package com.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.FasciaOraria;
import com.services.fascia_oraria.FasciaOrariaService;
@RestController
@RequestMapping("/fasciaoraria")
public class FasciaOrariaRestController {

	
		
	@Autowired
		private FasciaOrariaService service;
		
		// API per ottenere tutti i publisher
		// http://localhost:8080/api/v1/fasciaOraria    
		
		@GetMapping("/all")
	
		
		public ResponseEntity<?> findAll() throws Exception{
			List<FasciaOraria> fascOraria = service.findAll();
			if(!fascOraria.isEmpty()) {
				return new ResponseEntity<>(fascOraria,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Fascia oraria non trovata",HttpStatus.OK);
				// QUI METTIAMO OK PERCHE NON CE UN ELENCO,QUINDI NON CONSIDERATO ERRORE
			}
		}
		
		/*
		@PostMapping("/salva/fasciaoraria")
		public String saveCsv()throws Exception{
			service.saveCsv();
			return "File caricato con successo";
		}
		*/
		
		
		
		
}
