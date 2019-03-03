package br.com.prova.campanha.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Usuario;
import br.com.prova.campanha.dto.request.UsuarioDTORequest;
import br.com.prova.campanha.dto.response.CampanhaDTOResponse;
import br.com.prova.campanha.mapper.CampanhaMapper;
import br.com.prova.campanha.mapper.UsuarioMapper;
import br.com.prova.campanha.service.UsuarioService;

@RestController
@RequestMapping("/prova/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioMapper mapper;

	@Autowired
	private CampanhaMapper campanhaMapper;

	@PostMapping
	public List<CampanhaDTOResponse> criaUsuario(@RequestBody @Valid UsuarioDTORequest dtoRequest) {
		Usuario usuario = mapper.toObject(dtoRequest);
		List<Campanha> campanhas = service.salvaUsuario(usuario);
		return campanhaMapper.toDTOs(campanhas);
	}

	// faz associação usuario x campanha
}
