package br.com.prova.campanha.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.prova.campanha.model.Historico;

@Repository
public interface HistoricoRepository extends MongoRepository<Historico, String> {

	Historico findByData(LocalDate data);

}
