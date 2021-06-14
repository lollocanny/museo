package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CaricaFile;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.validator.ArtistaValidator;

@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
	@Autowired
	private ArtistaValidator artistaValidator;
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
	public String getArtista(@PathVariable("id") Long id, Model model) {
		
		logger.debug("getArtista");
		try {
			Artista a = artistaService.getArtista(id);
			model.addAttribute("artista", a);
			model.addAttribute("opere", a.getOpere());
			
			return "artista";
			
		} catch (NoSuchElementException e)
		{
			model.addAttribute("message", "Non è stato trovato nessun autore");
			return "error";
		}
	}

}
