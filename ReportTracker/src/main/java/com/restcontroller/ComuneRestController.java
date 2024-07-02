package com.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Comune;
import com.services.comune.ComuneService;

@RestController
@RequestMapping("comune")
public class ComuneRestController {

	@Autowired
	private ComuneService service;

	// API per ottenere tutti i comune
	// http://localhost:8080/comune/all
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Comune> comunes = service.findAll();
		if(!comunes.isEmpty()) {
			return new ResponseEntity<>(comunes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Nessun comune trovato", HttpStatus.OK);
		}
	}
	
	/*
	@PostMapping("/save/comuni")
	public String saveCSV()throws Exception{
		service.saveCSV();
		return "File caricato con successo";
	}*/
}
