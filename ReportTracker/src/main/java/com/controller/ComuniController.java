package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.repositories.ComuneRepository;




@Controller
@RequestMapping("/comuni")
public class ComuniController {
	
	@Autowired
	private ComuneRepository repository;

	@GetMapping("")
	public String index() {
		return "/comuni/comuniIndex";
	}
	
	@GetMapping("/create")
	public String create() {
		return "/comuni/create";
	}
	
	@GetMapping("/update/{id}")
	public String update() {
		return "/comuni/update";
	}
}
