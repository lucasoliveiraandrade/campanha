package br.com.prova.campanha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.campanha.collection.Campanha;

@Repository
public interface CampanhaRepository extends MongoRepository<Campanha, String> {

	List<Campanha> findAllByDataTerminoAfter(LocalDate dataTermino);

	// List<Campanha> findByStatusAndDataInicioAndDataTerminoBetween(String status,
	// LocalDate dataInicio, LocalDate dataTermino);

	List<Campanha> findAllByDataTermino(LocalDate dataTermino);

	List<Campanha> findAllByStatusAndDataTerminoGreaterThan(String status, LocalDate data);
}
