package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.service.CredenzialiService;

@Controller
public class LoginController {

	@Autowired
	private CredenzialiService credenzialiService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "login.html";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {
		UserDetails dettagliUtente = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Credenziali credenziali = credenzialiService.getCredenziali(dettagliUtente.getUsername());
	    
	    if (credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
	    	model.addAttribute("artista", new Artista());
	    	return "admin/gestisci";
	    }
	    return "index";
	}
}
