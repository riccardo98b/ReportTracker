package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tipologiecrimini")
public class TipologieCriminiController {
	
	@GetMapping("")
	public String index() {
		//return "/tipologia crimine/index";
		return "/tipologiecrimini/index";

	}
	
	@GetMapping("/create")
	public String create() {
		//return "/tipologia crimine/create";
		return "/tipologiecrimini/create";

	}
	
	@GetMapping("/update/{id}")
	public String update() {
		//return "/tipologiecrimine/update";
		return "/tipologiecrimini/update";

		
	}

}
