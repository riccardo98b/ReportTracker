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
	
	@GetMapping("/create")
	public String create() {
		return "/segnalazioni/create";
	}
	
	@GetMapping("/update")
	public String update() {
		return "/segnalazioni/update";
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "/guest/segnalazioniDetail";
	}
	
	
}