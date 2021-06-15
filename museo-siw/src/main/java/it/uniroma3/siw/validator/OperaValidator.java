package it.uniroma3.siw.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component
public class OperaValidator implements Validator {
	
	@Autowired
	private OperaService operaService;
	
  
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	
	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immagine", "required");
		

		if (!errors.hasErrors()) {
			
			
			if (this.operaService.alreadyExists((Opera)obj)) {
				
				errors.reject("duplicato");
			}
		}
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}
}
