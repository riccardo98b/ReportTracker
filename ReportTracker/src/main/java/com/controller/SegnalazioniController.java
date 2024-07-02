package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Segnalazione;
import com.services.segnalazione.SegnalazioneServiceImpl;

@RestController 
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
}
