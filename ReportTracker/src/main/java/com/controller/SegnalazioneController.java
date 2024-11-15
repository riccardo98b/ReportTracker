package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/segnalazioni")
public class SegnalazioneController {
	
	@GetMapping("")
	public String indexGuest() {
		return "/guest/segnalazioni";
	}
	
	@GetMapping("/indexAD")
	public String index() {
		return "/segnalazioni/segnalazioniIndexDelete";
	}
	
	@GetMapping("/logged")
	public String segnalazioneLogged() {
		return "/utenti/segnalazioniLogged";
	}
	
	// PAGINA SEGNALAZIONI ADMIN
	@GetMapping("/segnadmin")
	public String segnalazioneAD() {
		return "/admin/segnalazioniAD";
	}
	
	@GetMapping("/create")
	public String create() {
		return "/segnalazioni/create";
	}
	
	@GetMapping("/updateAD/{id}")
	public String updateAD() {
		return "/admin/update";
	}
	
	@GetMapping("/my")
	public String my() {
		return "/utenti/detail";
	}
	
	@GetMapping("/update/{id}")
	public String update() {
		return "/utenti/update";
	}
}
