package com.restcontroller;

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
import com.model.TipologiaCrimine;
import com.services.tipologia_crimine.TipologiaCrimineService;


@Controller
@RequestMapping("/tipologie_criminiAD")
public class TipologiaCrimineRestController {
	@Autowired           
	private TipologiaCrimineService service;
	
		
	@GetMapping("/all") 
	public ResponseEntity<?> findAll(){  
		List<TipologiaCrimine> tipologiacrimini=service.findAll();
		System.out.println(tipologiacrimini);
		if (!tipologiacrimini.isEmpty()) {
			return new ResponseEntity<>(tipologiacrimini, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Tipologia crimine non trovata",HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		try {
			return new ResponseEntity<>(service.findbyId(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PostMapping("/create")  
	public ResponseEntity<TipologiaCrimine> save(@RequestBody TipologiaCrimine tipologiacrimine){   
		return new ResponseEntity<>(service.save(tipologiacrimine),HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/cancella/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		try {
			service.deleteById(id);
			return new ResponseEntity<>("TipologiaCrimine con id "+id+" cancellata", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @RequestBody TipologiaCrimine tipologiacrimine){
		try {
			return new ResponseEntity<>(service.updateById(id,tipologiacrimine), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

}
