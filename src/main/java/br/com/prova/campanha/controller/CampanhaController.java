package br.com.prova.campanha.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.dto.request.CampanhaDTORequest;
import br.com.prova.campanha.dto.response.CampanhaDTOResponse;
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
	@ResponseStatus(code = HttpStatus.CREATED)
	public String criaNovaCampanha(@RequestBody @Valid CampanhaDTORequest dtoRequest) {
		Campanha campanha = mapper.toObject(dtoRequest);
		return service.criaCampanha(campanha);
	}

	@GetMapping(path = "/{campanhaId}")
	public CampanhaDTOResponse buscaCampanha(@PathVariable @NotEmpty String campanhaId) {
		Campanha campanha = service.buscaPorId(campanhaId);
		return mapper.toDTO(campanha);
	}

	@DeleteMapping(path = "/{campanhaId}")
	public String excluiCampanha(@PathVariable @NotEmpty String campanhaId) {
		service.excluiPorId(campanhaId);
		return campanhaId;
	}

	@GetMapping
	public List<CampanhaDTOResponse> buscaCampanhas() {
		List<Campanha> campanhas = service.buscaCampanhasValidas();
		return mapper.toDTOs(campanhas);
	}

	@PutMapping
	public String alteraCampanha(@RequestBody @Valid CampanhaDTORequest dtoRequest) {
		Campanha campanha = mapper.toObject(dtoRequest);
		service.alteraCampanha(campanha);
		return campanha.getId();
	}

}
