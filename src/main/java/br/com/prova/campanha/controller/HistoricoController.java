package br.com.prova.campanha.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.dto.response.HistoricoDTOResponse;
import br.com.prova.campanha.mapper.HistoricoMapper;
import br.com.prova.campanha.model.Historico;
import br.com.prova.campanha.service.HistoricoService;

@RestController
@RequestMapping(value = "/api/v1/historicos")
public class HistoricoController {

	@Autowired
	private HistoricoService service;

	@Autowired
	private HistoricoMapper mapper;

	@GetMapping
	public HistoricoDTOResponse buscaPorData(
			@RequestParam(name = "data", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime data) {
		Historico historico = service.buscaHistoricoPorData(data);
		return mapper.toDTO(historico);
	}
}