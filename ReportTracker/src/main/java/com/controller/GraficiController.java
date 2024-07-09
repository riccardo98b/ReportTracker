package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraficiController {
	
	@RequestMapping("/statistiche")
	public String grafici() {
		return "/guest/statistiche";
	}
}
