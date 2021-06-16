package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.validator.CollezioneValidator;

@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	

	@Autowired
	private CollezioneValidator collezioneValidator;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET )
	public String getCollezionePerId(@PathVariable("id") Long id,  Model model){
		Collezione c = collezioneService.getCollezione(id);
		
		model.addAttribute("collezione", c);
		model.addAttribute("opere", c.getOpere());
		
		return "collezione";
	}

    @RequestMapping(value = "/collezione", method = RequestMethod.GET)
    public String getCollezione(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.getAllCollezioni());
    		return "collezioni";
    }

    
    @RequestMapping(value="/homePageGestisci/aggiungiCollezione", method=RequestMethod.GET)
	public String aggiungiCollezione(Model model) {
    	model.addAttribute("collezione", new Collezione());
		return "aggiungiCollezione.html";
	}
	
	@RequestMapping(value="/homePageGestisci/aggiungiCollezione", method=RequestMethod.POST)
	public String saveCollezione(@Valid @ModelAttribute Collezione collezione,
							  BindingResult bindingResult, Model model) {
	
		
		this.collezioneValidator.validate(collezione, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "aggiungiCollezione";
			
		}
		else {
		model.addAttribute(collezione);
		collezioneService.saveCollezione(collezione);
		}
		
		model.addAttribute("collezione", collezione);
		return "gestisci";
	}

}
