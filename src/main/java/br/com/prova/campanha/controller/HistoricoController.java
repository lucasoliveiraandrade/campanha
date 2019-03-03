package br.com.prova.campanha.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.collection.Historico;
import br.com.prova.campanha.dto.response.HistoricoDTOResponse;
import br.com.prova.campanha.mapper.HistoricoMapper;
import br.com.prova.campanha.service.HistoricoService;
import br.com.prova.campanha.util.DataUtil;

@RestController
@RequestMapping(value = "historicos")
public class HistoricoController {

	@Autowired
	private HistoricoService service;

	@Autowired
	private HistoricoMapper mapper;

	@Autowired
	private DataUtil dataUtil;

	@GetMapping
	public HistoricoDTOResponse buscaPorData(@RequestParam(name = "data", required = true) String data) {
		LocalDate dataLocalDate = dataUtil.converteStringParaLocalDate(data);
		Historico historico = service.buscaHistoricoPorData(dataLocalDate);
		return mapper.toDTO(historico);
	}
}
