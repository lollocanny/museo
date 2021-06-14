package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.validator.ArtistaValidator;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CaricaFile;


@Controller
public class AdminController {

	@Autowired
	private CredenzialiService credenzialiService;
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "login.html";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {
		UserDetails dettagliUtente = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    Credenziali credenziali = credenzialiService.getCredenziali(dettagliUtente.getUsername());
	    
	    if (credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
	    	model.addAttribute("artista", new Artista());
	    	return "homePageAdmin.html";
	    }
	    return "index.html";
	}
	
	@RequestMapping(value="/homePageAdmin", method = RequestMethod.GET)
	public String homePageAdmin() {
		return "homePageAdmin.html";
	}
	
	@RequestMapping(value="/homePageGestisci", method=RequestMethod.GET)
	public String homePageGestisci(@ModelAttribute("submit") String submit, Model model) {

		return "gestisci.html";
	}
	
	@RequestMapping(value="/homePageGestisci/aggiungiArtista", method=RequestMethod.GET)
	public String aggiungiOpera(Model model) {
		model.addAttribute("artista", new Artista());
		return "aggiungiArtista.html";
	}
	
	@RequestMapping(value="/homePageGestisci/aggiungiArtista", method=RequestMethod.POST)
	public String salvaArtista(@Valid @ModelAttribute Artista artista,
							  BindingResult bindingResult,Model model) {
		
		//this.artistaValidator.validate(artista, bindingResult);
		
		/*if(!bindingResult.hasErrors()) {
			model.addAttribute(artista);
			artistaService.saveArtista(artista);
			return "aggiungiArtista.html";
		}*/

		return "aggiungiArtista.html";
	}
}
