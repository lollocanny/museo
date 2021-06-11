package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Dipendente;
import it.uniroma3.siw.repository.DipendenteRepository;

@Service
public class DipendentiService {

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@Transactional
	public List<Dipendente> getAllDipendenti(){
		return (List<Dipendente>) dipendenteRepository.findAll();
	}
}
