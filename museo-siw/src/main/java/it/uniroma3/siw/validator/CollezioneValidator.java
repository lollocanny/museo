package it.uniroma3.siw.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	
	@Autowired
	private CollezioneService collezioneService;

	@Override
	public void validate(Object o, Errors errors) {
		
		
		Collezione collezione = (Collezione) o;
		
		String nome = collezione.getNome().trim();
		String descrizione = collezione.getDescrizione().trim();
		
		if (nome==null || nome.trim().isEmpty())
			   errors.rejectValue("nome", "required");
		else if(nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");
		  else if(this.collezioneService.alreadyExists(collezione))
		   errors.rejectValue("nome", "duplicate");
		
		if (descrizione==null || descrizione.trim().isEmpty())
			   errors.rejectValue("descrizione", "required");
		else if(descrizione.length() < MIN_NAME_LENGTH || descrizione.length() > MAX_NAME_LENGTH)
			errors.rejectValue("descrizione", "size");
		
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Collezione.class.equals(clazz);
	}
}
