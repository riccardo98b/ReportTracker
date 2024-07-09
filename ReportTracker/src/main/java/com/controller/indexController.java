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
	
	// Home per i guest
	@GetMapping("/")
	public String home() {
		return "guest/index";
	}
	
	// Home per l'admin (loggato)
	@GetMapping("/indexAD")
	public String homeAD() {
		return "admin/indexAD";
	}
	
	// Home per l'utente (loggato)
	@GetMapping("/indexLogged")
	public String homeUtente() {
		return "utenti/indexLogged";
	}
	
	
	
	@GetMapping("/errore404")
	public String errore404() {
		return "errore/errore";
	}
	
  @GetMapping("/registrazione")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Utente());
        return "login/registrazione";
    }

    @PostMapping("/registrazione")
    public String registerUser(@ModelAttribute Utente user, Model model) {
        try {
            userService.save(user);
            return "redirect:login/login";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while registering the user.");
            return "login/registrazione";
        }
    }

    @GetMapping("/accedi")
    public String showLoginForm() {
        return "/login/login";
    }

    @GetMapping("/home")
    public String homePage() {
        return "guest/index";
    }
}

