package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.services.utente.UtenteService;

@Controller
public class UtenteController {

	@Autowired
	private UtenteService service;
	
	@GetMapping("/")
	public String home() {
		return "/guest/index";
	}
}

