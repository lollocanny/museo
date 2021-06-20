package it.uniroma3.siw.controller;


import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

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

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.ArtistaService;
import it.uniroma3.siw.service.CaricaFile;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.service.OperaService;
import it.uniroma3.siw.validator.OperaValidator;

@Controller
public class OperaController {
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private ArtistaService artistaService;
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OperaValidator operaValidator;

    
	@RequestMapping(value = "/opera/{id}")
	public String getOpera(@PathVariable("id") Long id, Model model) {

		logger.debug("getOpera");
		try {
			Opera o = operaService.getOpera(id);
			model.addAttribute("opera", o);
			model.addAttribute("artista", o.getArtista());

			return "opera.html";

		} catch (NoSuchElementException e)
		{
			return "error";
		}
	}

    @RequestMapping(value = "/opera", method = RequestMethod.GET)
    public String getOpere(Model model) {
    		model.addAttribute("opere", this.operaService.getAllOpere());
    		return "opere.html";
    }


   
	@RequestMapping(value="/homePageGestisci/aggiungiOpera", method=RequestMethod.GET)
	public String aggiungiOpera(Model model) {
		model.addAttribute("opera", new Opera());
		model.addAttribute("collezioni", collezioneService.getAllCollezioni());
		model.addAttribute("artisti", artistaService.getAllArtisti());
		return "aggiungiOpera.html";
	}
	
	
	@RequestMapping(value="/homePageGestisci/aggiungiOpera", method=RequestMethod.POST)
	public String saveOpera(@Valid @ModelAttribute Opera opera,
			@RequestParam("foto") MultipartFile multipartFile,
			BindingResult bindingResult , Model model) throws IOException {
		
		
		
		this.operaValidator.validate(opera, bindingResult);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("artisti", artistaService.getAllArtisti());
			model.addAttribute("collezioni", collezioneService.getAllCollezioni());
			return "aggiungiOpera";
		}
		
		else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			opera.setImmagine(fileName);
			
			model.addAttribute(opera);
			operaService.saveOpera(opera);
			
			CaricaFile.saveFile(MvcConfig.imagesPath, fileName, multipartFile);
			
		}
		
		model.addAttribute("opera",opera);
		return "gestisci";
	}
	
	@RequestMapping(value="/homePageGestisci/rimuoviOpera", method=RequestMethod.GET)
	public String rimuoviOpera(Model model) {
		model.addAttribute("opere", this.operaService.getAllOpere());
		return "rimuoviOpera.html";
	}
	
	
	@RequestMapping(value = "/homePageGestisci/rimuoviOpera/{id}/remove", method = RequestMethod.POST)
    public String deleteOpera(@PathVariable long id,
     Model model) {
	 operaService.removeOpera(id);
	 return "gestisci.html";
    }

}
