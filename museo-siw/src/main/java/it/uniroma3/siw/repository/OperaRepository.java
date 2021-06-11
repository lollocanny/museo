package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Opera;

@Repository
public interface OperaRepository extends CrudRepository<Opera, Long> {
	
	public Optional<Opera> findByTitolo(String titolo);
}
