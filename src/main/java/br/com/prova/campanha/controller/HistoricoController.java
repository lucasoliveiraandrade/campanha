package br.com.prova.campanha.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.collection.Historico;
import br.com.prova.campanha.dto.response.HistoricoDTOResponse;
import br.com.prova.campanha.mapper.HistoricoMapper;
import br.com.prova.campanha.service.HistoricoService;

@RestController
@RequestMapping(value = "historicos")
public class HistoricoController {

	@Autowired
	private HistoricoService service;

	@Autowired
	private HistoricoMapper mapper;

	@GetMapping
	public HistoricoDTOResponse buscaPorData(@RequestParam(name = "data", required = true) String data) {
		LocalDate dataLocalDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Historico historico = service.buscaHistoricoPorData(dataLocalDate);
		return mapper.toDTO(historico);
	}
}
