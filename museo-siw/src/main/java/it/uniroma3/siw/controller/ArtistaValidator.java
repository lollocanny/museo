package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.service.ArtistaService;

@Component
public class ArtistaValidator implements Validator {
	@Autowired
	private ArtistaService artistaService;
	
    private static final Logger logger = LoggerFactory.getLogger(ArtistaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoNascita", "required");
		
		Artista a = (Artista)o;
		
		//if("".equals(a.getLuogoMorte())) {
		//	a.setLuogoMorte(null);
		//}
		
		//if(a.getDataMorte() != null && a.getDataNascita().isAfter(a.getDataMorte())) {
			//errors.reject("dataNascita");
			//return;
		//}

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori richiesti non nulli");
			
			/*if (this.artistaService.alreadyExists((Artista)o)) {
				logger.debug("e' un duplicato");
				
				errors.reject("duplicato");
			}*/
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}
}
