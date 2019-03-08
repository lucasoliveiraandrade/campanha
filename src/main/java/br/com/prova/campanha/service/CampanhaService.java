package br.com.prova.campanha.service;

import static br.com.prova.campanha.enumeration.StatusCampanha.ATIVADA;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.prova.campanha.model.Campanha;
import br.com.prova.campanha.repository.CampanhaRepository;
import br.com.prova.campanha.validacao.CampanhaValidacao;

@Service
public class CampanhaService {

	@Autowired
	private CampanhaRepository repositorio;

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
			return repositorio.insert(campanha).getId();
		}

		campanhasDB.stream().forEach(c -> c.setDataTermino(c.getDataTermino().plusDays(1)));
		repositorio.saveAll(campanhasDB);
		historicoService.salvaHistorico(campanhasDB);

		return salvaCampanhaRecursivamente(campanha);
	}

	public Campanha buscaPorId(String campanhaId) {
		validador.validaCampanhaId(campanhaId);
		validador.validaExistenciaCampanha(repositorio.existsById(campanhaId));

		Campanha campanha = repositorio.findById(campanhaId).get();
		validador.validaCampanhaComVigenciaVencida(campanha);
		return campanha;
	}

	public List<Campanha> buscaPorIds(List<String> campanhaIds) {
		if (CollectionUtils.isEmpty(campanhaIds)) {
			return new ArrayList<>();
		}

		List<Campanha> campanhas = new ArrayList<>();
		repositorio.findAllById(campanhaIds).forEach(campanhas::add);
		return campanhas;
	}

	public void excluiPorId(String campanhaId) {
		validador.validaCampanhaId(campanhaId);
		validador.validaExistenciaCampanha(repositorio.existsById(campanhaId));
		repositorio.deleteById(campanhaId);
	}

	public List<Campanha> buscaCampanhasValidas(String timeCoracaoId) {
		List<Criteria> criterias = new ArrayList<>();

		criterias.add(Criteria.where("dataTermino").gte(LocalDate.now()));

		if (StringUtils.isNotBlank(timeCoracaoId)) {
			criterias.add(Criteria.where("timeCoracaoId").is(timeCoracaoId));
		}

		Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));

		return mongoTemplate.find(new Query(criteria), Campanha.class);
	}

	public void alteraCampanha(Campanha campanha) {
		validador.validaCampanhaId(campanha.getId());
		validador.validaExistenciaCampanha(repositorio.existsById(campanha.getId()));

		Campanha campanhaDB = repositorio.findById(campanha.getId()).get();
		campanhaDB.setNome(campanha.getNome());
		campanhaDB.setTimeCoracaoId(campanha.getTimeCoracaoId());
		campanhaDB.setDataInicio(campanha.getDataInicio());
		campanhaDB.setDataTermino(campanha.getDataTermino());

		repositorio.save(campanhaDB);
	}

	private List<Campanha> buscaCampanhasComMesmaVigencia(Campanha campanhaNova) {
		Criteria criteria = Criteria.where("status").is(ATIVADA)
							 .and("dataInicio").gte(campanhaNova.getDataInicio())
							 .and("dataTermino").lte(campanhaNova.getDataTermino());

		return mongoTemplate.find(new Query().addCriteria(criteria), Campanha.class);
	}

	private String salvaCampanhaRecursivamente(Campanha campanha) {
		List<Campanha> campanhasDB = repositorio.findAllByDataTermino(campanha.getDataTermino());

		if (!isEmpty(campanhasDB)) {
			campanha.setDataTermino(campanha.getDataTermino().plusDays(1));
			salvaCampanhaRecursivamente(campanha);
		}

		campanha.setStatus(ATIVADA);
		return repositorio.save(campanha).getId();
	}

	public List<Campanha> buscaCampanhasValidasPorTimeCoracao(String timeCoracaoId) {
		if (isBlank(timeCoracaoId)) {
			return new ArrayList<>();
		}

		List<Campanha> campanhas = new ArrayList<>();
		repositorio.findAllByTimeCoracaoIdAndDataTerminoAfter(timeCoracaoId, LocalDate.now()).forEach(campanhas::add);
		return campanhas;
	}
}