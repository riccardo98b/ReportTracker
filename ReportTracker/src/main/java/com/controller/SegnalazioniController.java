package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Segnalazione;
import com.services.segnalazione.SegnalazioneServiceImpl;

@Controller 
@RequestMapping("/segnalazioni")
public class SegnalazioniController {

	@Autowired
	private SegnalazioneServiceImpl service;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Segnalazione> segnalazione = service.findAll();
		
		if(!segnalazione.isEmpty()){
			return new ResponseEntity<>(segnalazione, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No author found", HttpStatus.OK);
		}
	}
	
	@PostMapping("/save/segnalazione")
	public ResponseEntity<?> save(@RequestBody Segnalazione segnalazione){
		try {
			return new ResponseEntity<>(service.save(segnalazione), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
	
	
}
