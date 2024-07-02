package com.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.model.TipologiaCrimine;
import com.services.tipologia_crimine.TipologiaCrimineService;

@RestController
@RequestMapping("/tipologie_crimini")
public class TipologiaCrimineRestController {
	
	@Autowired           
	private TipologiaCrimineService service;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){  
		List<TipologiaCrimine> tipologiacrimini=service.findAll();
		if (!tipologiacrimini.isEmpty()) {
			return new ResponseEntity<>(tipologiacrimini,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("tipologiacrimine non trovata",HttpStatus.OK);
		}
	}
	
	//http://localhost:8080/tipologie_crimini
	/*
	@PostMapping("/caricatutto")
	public void saveCSV()throws Exception{
		service.saveCSV();
	}
	*/
	

}
