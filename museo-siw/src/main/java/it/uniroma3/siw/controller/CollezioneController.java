package it.uniroma3.siw.controller;

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
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.validator.CollezioneValidator;

@Controller
public class CollezioneController {
	@Autowired
	private CollezioneService collezioneService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/collezione/{nome}", method = RequestMethod.GET )
	public String getCollezionePerId(@PathVariable("nome") String nome,  Model model){
		Collezione c = collezioneService.getCollezione(nome);
		
		model.addAttribute("collezione", c);
		model.addAttribute("opere", c.getOpere());
		
		return "collezione";
	}

    @RequestMapping(value = "/collezione", method = RequestMethod.GET)
    public String getArtisti(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.getAllCollezioni());
    		return "collezioni";
    }
}
    


/*	
		@RequestMapping(value = {"/visualizzaCollezioni"}, method= RequestMethod.GET)
	public String visualizzaCollezioni(Model model) {
		logger.debug("visualizzaCollezioni");
		return "collezioni.html";
	}
}
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CollezioneValidator collezioneValidator;
	
	@RequestMapping(value = "/collezione/{nome}", method = RequestMethod.GET )
	public String getCollezionePerId(@PathVariable("nome") String nome,  Model model){
		Collezione c = collezioneService.getCollezione(nome);
		
		model.addAttribute("collezione", c);
		model.addAttribute("opere", c.getOpere());
		
		return "collezione";
	}
	
	@RequestMapping(value="/admin/collezione/save", method=RequestMethod.POST)
	public String saveArtista(@ModelAttribute("collezione") Collezione collezione, 
							  @ModelAttribute("submit") String submit, 
							  BindingResult bindingResult, Model model) {
		
		if("indietro".equals(submit)) {
			return "admin/gestisci";
		}
		
		collezioneValidator.validate(collezione, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			
			collezioneService.saveCollezione(collezione);
			
			return "admin/gestisci";
		}
		
		model.addAttribute("collezione", collezione);
		return "admin/collezione-form";
	}

}
*/