package it.uniroma3.siw.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component
public class OperaValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	
	@Autowired
	private OperaService operaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Opera opera = (Opera) o;
		String titolo = opera.getTitolo().trim();
		String descrizione = opera.getDescrizione().trim();
		
		if (titolo==null || titolo.trim().isEmpty())
			   errors.rejectValue("titolo", "required");
		else if(titolo.length() < MIN_NAME_LENGTH || titolo.length() > MAX_NAME_LENGTH)
			errors.rejectValue("titolo", "size");
		  else if(this.operaService.alreadyExists(opera))
		   errors.rejectValue("titolo", "duplicate");
		
		if (descrizione==null || descrizione.trim().isEmpty())
			   errors.rejectValue("descrizione", "required");
		else if(descrizione.length() < MIN_NAME_LENGTH || descrizione.length() > MAX_NAME_LENGTH)
			errors.rejectValue("descrizione", "size");
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Opera.class.equals(clazz);
	}
	
	
}
