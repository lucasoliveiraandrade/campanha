package br.com.prova.campanha.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.dto.request.CampanhaDTORequest;
import br.com.prova.campanha.mapper.CampanhaMapper;
import br.com.prova.campanha.service.CampanhaService;

@RestController
@RequestMapping("campanhas")
public class CampanhaController {

	@Autowired
	private CampanhaService service;

	@Autowired
	private CampanhaMapper mapper;

	@PostMapping
	public String criaNovaCampanha(@RequestBody @Valid CampanhaDTORequest dto) {
		Campanha campanha = mapper.toObject(dto);
		return service.criaCampanha(campanha);
	}

}
