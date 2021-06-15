package it.uniroma3.siw.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.repository.CollezioneRepository;


@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;

	
	@Transactional
	public void saveCollezione(Collezione c) {
<<<<<<< HEAD
		collezioneRepository.save(c);
=======
			collezioneRepository.save(c);
>>>>>>> main
	}
	
	@Transactional
	public Collezione getCollezione(String nome) throws NoSuchElementException {
		return collezioneRepository.findByNome(nome).get();
	}
	
	@Transactional
	public List<Collezione> getAllCollezioni() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Collezione c) {
		return collezioneRepository.findByNome(c.getNome()).orElse(null) != null;
	}
	
	@Transactional
	public void removeCollezione(String nome) {
		collezioneRepository.deleteByNome(nome);
	}
}
