package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.services.fascia_oraria.FasciaOrariaService;

@Controller
@RequestMapping("/fascia_oraria")
public class FasciaOrariaController {
	@Autowired
	public FasciaOrariaService service;
	
	@GetMapping("")
	public String index() {
		return "/fascia_oraria/Index";
	}
	
	@GetMapping("/create")
	public String create() {
		return "/fascia_oraria/create";
	}
	
	@GetMapping("/update/{id}")
	public String update() { 
		
		
		return "fascia_oraria/update";
		
	}

}
