package br.com.prova.campanha.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public Campanha buscaPorId(String campanhaId) {
		if (campanhaId == null) {
			throw new RuntimeException("Identificador da campanha obrigatório.");
		}

		Campanha campanha = repository.findById(campanhaId)
				.orElseThrow(() -> new RuntimeException("Campanha não encontrada."));

		if (campanha.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Campanha com data de vigência vencida.");
		}

		return campanha;
	}

	public void excluiPorId(String campanhaId) {
		if (campanhaId == null) {
			throw new RuntimeException("Identificador da campanha obrigatório.");
		}

		if (!repository.existsById(campanhaId)) {
			throw new RuntimeException("Campanha não encontrada.");
		}

		repository.deleteById(campanhaId);
	}

	public List<Campanha> buscaCampanhasValidas() {
		List<Campanha> campanhas = new ArrayList<>();
		repository.findByDataTerminoAfter(LocalDate.now()).forEach(campanhas::add);
		return campanhas;
	}

	public void alteraCampanha(Campanha campanha) {
		if (campanha.getId() == null) {
			throw new RuntimeException("Identificador da campanha obrigatório.");
		}

		if (!repository.existsById(campanha.getId())) {
			throw new RuntimeException("Campanha não encontrada.");
		}

		Campanha campanhaDB = repository.findById(campanha.getId()).get();
		campanhaDB.setNome(campanha.getNome());
		campanhaDB.setTimeCoracao(campanha.getTimeCoracao());
		campanhaDB.setDataInicio(campanha.getDataInicio());
		campanhaDB.setDataTermino(campanha.getDataTermino());

		repository.save(campanhaDB);
	}
}
