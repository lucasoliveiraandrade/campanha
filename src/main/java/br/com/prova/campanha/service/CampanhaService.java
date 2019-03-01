package br.com.prova.campanha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.repository.CampanhaRepository;

@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository repository;

	public String criaCampanha(Campanha campanha) {
		return repository.insert(campanha).getId();
	}
}
