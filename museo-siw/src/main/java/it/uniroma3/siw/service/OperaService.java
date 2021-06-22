package it.uniroma3.siw.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.ArtistaRepository;
import it.uniroma3.siw.repository.CollezioneRepository;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public void saveOpera(Opera o) {
		operaRepository.save(o);
	}
	
	@Transactional
	public void saveOpera(Opera o, String artista_id, String collezione_nome) {
		Artista a = artistaRepository.findById(Long.parseLong(artista_id)).orElse(null);
		Collezione c = collezioneRepository.findByNome(collezione_nome).orElse(null);
		
		if(a == null || c == null) {
			return;
		}
		
		o.setArtista(a);
		o.setCollezione(c);
		
		operaRepository.save(o);
	}
	
	@Transactional
	public Opera getOpera(Long id) throws NoSuchElementException {
		return operaRepository.findById(id).get();
	}
	
	@Transactional
	public List<Opera> getAllOpere() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Opera o) {
		return operaRepository.findByTitolo(o.getTitolo()).orElse(null) != null;
	}
	
	@Transactional
	public void removeOpera(Long id) {
		operaRepository.deleteById(id);
	}
	
}
