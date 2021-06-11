package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

	public Optional<Dipendente> findByMatricola(String matricola);

}
