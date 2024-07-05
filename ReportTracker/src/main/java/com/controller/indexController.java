package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Utente;
import com.services.utente.UtenteService;




@Controller
public class indexController {

	@Autowired
	private UtenteService userService;
	
	@GetMapping("/")
	public String home() {
		return "/guest/index";
	}
	
  @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente());
        return "/gues/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente user, Model model) {
        try {
            userService.save(user);
            return "redirect:/guest/login";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while registering the user.");
            return "/gues/tregister";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/guest/login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "/user/index";
    }
}

