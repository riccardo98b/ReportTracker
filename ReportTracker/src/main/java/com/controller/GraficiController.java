package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraficiController {
	
	@GetMapping("/statistiche")
	public String graficiGuest() {
		return "/guest/statistiche";
	}
	
	@GetMapping("/statisticheLogged")
	public String grafici() {
		return "/utenti/statisticheLogged";
	}
	
}
