package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.service.ArtistaService;

@Component
public class ArtistaValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	
	@Autowired
	private ArtistaService artistaService;

	@Override
	public void validate(Object o, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");
		
		Artista a = (Artista)o;
		
		if("".equals(a.getLuogoMorte())) {
			a.setLuogoMorte(null);
		}

		if (!errors.hasErrors()) {
			
			if (this.artistaService.alreadyExists((Artista)o)) {
				errors.reject("duplicato");
			}
		}
	}
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}
}
