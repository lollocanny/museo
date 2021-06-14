package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
	
		
		Artista a = (Artista)o;
		
		String nome = a.getNome().trim();
		String cognome = a.getCognome().trim();
		String nazionalita = a.getNazionalita();
		
		 if (nome==null || nome.trim().isEmpty())
			   errors.rejectValue("nome", "required");
			  else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			   errors.rejectValue("nome", "size");
			  

		  if (cognome==null || cognome.trim().isEmpty())
			   errors.rejectValue("cognome", "required");
			  else if (cognome.length() < MIN_NAME_LENGTH || cognome.length() > MAX_NAME_LENGTH)
			   errors.rejectValue("cognome", "size");
			  
		  if (nazionalita==null || nazionalita.trim().isEmpty())
			   errors.rejectValue("nazionalita", "required");
			  else if (nazionalita.length() < MIN_NAME_LENGTH || nazionalita.length() > MAX_NAME_LENGTH)
			   errors.rejectValue("nazionalita", "size");
			
			if (this.artistaService.alreadyExists((Artista)o)) {
				errors.reject("duplicato");
			}
		}
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}
}
