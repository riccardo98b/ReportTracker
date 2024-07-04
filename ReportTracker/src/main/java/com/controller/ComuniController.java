package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Comune;
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
	
/*	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		Optional<Comune> c = repository.findById(id);
		
		if (c.isEmpty()) {
			return "redirect:comuni/comuniIndex";
		}
		
		
		model.addAttribute("comune", c.get());
		
		
		return "/comuni/update";
	}*/
	
	@GetMapping("/update/{id}")
	public String update() {
		return "/comuni/update";
	}
}
