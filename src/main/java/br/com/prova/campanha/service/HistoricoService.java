package br.com.prova.campanha.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Historico;
import br.com.prova.campanha.repository.HistoricoRepository;
import br.com.prova.campanha.validacao.HistoricoValidacao;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repository;

	@Autowired
	private HistoricoValidacao validador;

	public void salvaHistorico(List<Campanha> campanhasAlteradas) {
		if (CollectionUtils.isEmpty(campanhasAlteradas)) {
			return;
		}

		List<String> campanhasId = campanhasAlteradas.stream().map(c -> c.getId()).collect(Collectors.toList());

		Historico historicoDB = repository.findByData(LocalDate.now());

		if (historicoDB == null) {
			Historico historico = Historico.builder().data(LocalDate.now()).campanhasAlteradas(campanhasId).build();
			repository.save(historico);
		} else {
			Set<String> campanhasIdSet = new HashSet<>();
			campanhasIdSet.addAll(historicoDB.getCampanhasAlteradas());
			campanhasIdSet.addAll(campanhasId);

			historicoDB.setCampanhasAlteradas(new ArrayList<String>(campanhasIdSet));
			repository.save(historicoDB);
		}
	}

	public Historico buscaHistoricoPorData(LocalDate data) {
		validador.validaDataHistorico(data);
		Historico historico = repository.findByData(data);
		validador.validaHistorico(historico);
		return historico;
	}
}
