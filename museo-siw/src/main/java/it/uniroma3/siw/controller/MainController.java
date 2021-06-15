package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Credenziali;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.CredenzialiService;
import it.uniroma3.siw.service.OperaService;

@Controller
public class MainController {
	
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}



	@Autowired
	private CredenzialiService credenzialiService;

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private CollezioneService collezioneService;

	@Autowired
	private OperaService operaService;


	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private void setModelRole(Model model) {
		boolean isAdmin = false;
		
		try {
			UserDetails dettagliUtente = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credenziali credenziali = credenzialiService.getCredenziali(dettagliUtente.getUsername());
			
			isAdmin = Credenziali.ADMIN_ROLE.equals(credenziali.getRole());
			
		} catch(Exception e) {}
		
		model.addAttribute("isAdmin", isAdmin);
	}

	@RequestMapping(value = "/artista")
	public String getArtisti(Model model) {

		logger.debug("getArtisti");

		model.addAttribute("artisti", artistaService.getAllArtisti());

		setModelRole(model);

		return "artisti";
	}


	@RequestMapping(value="/collezione", method = RequestMethod.GET)
	public String getCollezioni(Model model) {

		logger.debug("getCollezioni");

		model.addAttribute("collezioni", collezioneService.getAllCollezioni());

		setModelRole(model);

		return "collezioni.html";
	}

	@RequestMapping(value = "/opera")
	public String getOpere(Model model) {

		logger.debug("getOpere");

		model.addAttribute("opere", operaService.getAllOpere());

		setModelRole(model);

		return "opere.html";
	}
	
	@RequestMapping(value="/admin/artista/remove/{id}", method=RequestMethod.GET)
	public String removeArtista(@PathVariable("id") Long id, 
								Model model) {
		
		artistaService.removeArtista(id);
		
		return getArtisti(model);
	}
	
	@RequestMapping(value="/admin/opera/remove/{id}", method=RequestMethod.GET)
	public String removeOpera(@PathVariable("id") Long id, 
			Model model) {
		
		operaService.removeOpera(id);
		
		return getOpere(model);
	}
	
	@RequestMapping(value="/admin/collezione/remove/{nome}", method=RequestMethod.GET)
	public String removeCollezione(@PathVariable("nome") String nome, 
								   Model model) {
		
		collezioneService.removeCollezione(nome);
		
		return getCollezioni(model);
	}
	
	
	@RequestMapping(value="/admin/menu", method=RequestMethod.GET)
	public String menu(Model model) {
		return "admin/gestisci";
	}
	
}
