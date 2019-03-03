package br.com.prova.campanha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Usuario;
import br.com.prova.campanha.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private CampanhaService campanhaService;

	public List<Campanha> salvaUsuario(Usuario usuario) {
		repository.insert(usuario);

		List<Campanha> campanhasValidas = new ArrayList<>();

		if (CollectionUtils.isEmpty(usuario.getCampanhas())) {
			campanhasValidas = campanhaService.buscaCampanhasValidas();
		}

		return campanhasValidas;
	}
}
