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
import org.springframework.web.bind.annotation.RestController;

import com.model.Comune;
import com.services.comune.ComuneService;

@RestController
@RequestMapping("/comuneAD")
public class ComuneRestController {

	@Autowired
	private ComuneService service;

	// API per ottenere tutti i comune
	// http://localhost:8080/comuneAD/all
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Comune> comune = service.findAll();
		if(!comune.isEmpty()) {
			return new ResponseEntity<>(comune, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Nessun comune trovato", HttpStatus.OK);
		}
	}

	// API per ottenere il comune di id specificato
	// http://localhost:8080/comuneAD/find/id
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		try {
			return new ResponseEntity<>(service.findbyId(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// API per salvare un comune
	// http://localhost:8080/comuneAD/save
	@PostMapping("/save")
	public ResponseEntity<Comune> save(@RequestBody Comune comune){
		return new ResponseEntity<>(service.save(comune), HttpStatus.OK);
	}

	// API per cancellare un comune di id specificato
	// http://localhost:8080/comuneAD/delete/id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		try {
			service.deleteById(id);
			return new ResponseEntity<>("Comune con id:"+id+" cancellato", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	// API per aggiornare il comune di id specificato
	// http://localhost:8080/comuneAD/update/id
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Comune comune){
		try {
			return new ResponseEntity<>(service.updateById(id, comune), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
