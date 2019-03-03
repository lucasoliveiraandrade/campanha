package br.com.prova.campanha.service;

import static br.com.prova.campanha.enumeration.StatusCampanha.ATIVADA;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.repository.CampanhaRepository;
import br.com.prova.campanha.validacao.CampanhaValidacao;

@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository repository;

	@Autowired
	private CampanhaValidacao validador;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private HistoricoService historicoService;

	public String salvaCampanha(Campanha campanha) {
		List<Campanha> campanhasDB = buscaCampanhasComMesmaVigencia(campanha);

		if (isEmpty(campanhasDB)) {
			campanha.setStatus(ATIVADA);
			return repository.insert(campanha).getId();
		}

		campanhasDB.stream().forEach(c -> c.setDataTermino(c.getDataTermino().plusDays(1)));
		repository.saveAll(campanhasDB);
		historicoService.salvaHistorico(campanhasDB);

		return salvaCampanhaRecursivamente(campanha);
	}

	public Campanha buscaPorId(String campanhaId) {
		validador.validaCampanhaId(campanhaId);
		validador.validaCampanhaExistente(repository.existsById(campanhaId));

		Campanha campanha = repository.findById(campanhaId).get();
		validador.validaCampanhaComVigenciaVencida(campanha);
		return campanha;
	}

	public void excluiPorId(String campanhaId) {
		validador.validaCampanhaId(campanhaId);
		validador.validaCampanhaExistente(repository.existsById(campanhaId));
		repository.deleteById(campanhaId);
	}

	public List<Campanha> buscaCampanhasValidas() {
		List<Campanha> campanhas = new ArrayList<>();
		repository.findByDataTerminoAfter(LocalDate.now()).forEach(campanhas::add);
		return campanhas;
	}

	public void alteraCampanha(Campanha campanha) {
		validador.validaCampanhaId(campanha.getId());
		validador.validaCampanhaExistente(repository.existsById(campanha.getId()));

		Campanha campanhaDB = repository.findById(campanha.getId()).get();
		campanhaDB.setNome(campanha.getNome());
		campanhaDB.setTimeCoracaoId(campanha.getTimeCoracaoId());
		campanhaDB.setDataInicio(campanha.getDataInicio());
		campanhaDB.setDataTermino(campanha.getDataTermino());

		repository.save(campanhaDB);
	}

	private List<Campanha> buscaCampanhasComMesmaVigencia(Campanha campanhaNova) {
		Query criteria = new Query().addCriteria(Criteria.where("status").is(ATIVADA).and("dataInicio")
				.gte(campanhaNova.getDataInicio()).and("dataTermino").lte(campanhaNova.getDataTermino()));

		return mongoTemplate.find(criteria, Campanha.class);
	}

	private String salvaCampanhaRecursivamente(Campanha campanha) {
		List<Campanha> campanhasDB = repository.findAllByDataTermino(campanha.getDataTermino());

		if (!isEmpty(campanhasDB)) {
			campanha.setDataTermino(campanha.getDataTermino().plusDays(1));
			salvaCampanhaRecursivamente(campanha);
		}

		campanha.setStatus(ATIVADA);
		return repository.save(campanha).getId();
	}
}