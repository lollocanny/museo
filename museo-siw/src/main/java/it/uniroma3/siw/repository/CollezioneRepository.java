package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Collezione;

@Repository
public interface CollezioneRepository extends CrudRepository<Collezione, Long>{
	
	public Optional<Collezione> findByNome(String nome);
	
	public void deleteByNome(String nome);
}
