package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/comuni")
public class ComuniController {

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
