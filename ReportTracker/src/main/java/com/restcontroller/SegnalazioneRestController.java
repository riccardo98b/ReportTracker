package com.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Segnalazione;
import com.services.segnalazione.SegnalazioneService;

@RestController
@RequestMapping("segnalazione")
public class SegnalazioneRestController {
	
	@Autowired
	private SegnalazioneService service;
	
	@GetMapping("/all")
	public ResponseEntity<?>findAll(){
		List<Segnalazione> segnalazioni=service.findAll();
		if (!segnalazioni.isEmpty()) {
			return new ResponseEntity<>(segnalazioni,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Non sono presenti presenti",HttpStatus.OK);
		}
		
		
	}
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	

}
