package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CuratoreRepository;

@Service
public class CuratoreService {

	@Autowired
	private CuratoreRepository dipendenteRepository;
	
	@Transactional
	public List<Curatore> getAllDipendenti(){
		return (List<Curatore>) dipendenteRepository.findAll();
	}
}
