package br.com.prova.campanha.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Usuario;
import br.com.prova.campanha.enumeration.TipoUsuario;
import br.com.prova.campanha.repository.UsuarioRepository;
import br.com.prova.campanha.validacao.UsuarioValidacao;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private CampanhaService campanhaService;

	@Autowired
	private UsuarioValidacao validador;

	public List<Campanha> salvaUsuario(Usuario usuario) {
		validador.validaUsuarioJaExistente(repository.existsByEmail(usuario.getEmail()));

		usuario.setTipo(TipoUsuario.CLIENTE);
		repository.insert(usuario);

		List<Campanha> campanhasValidas = new ArrayList<>();

		if (CollectionUtils.isEmpty(usuario.getCampanhas())) {
			campanhasValidas = campanhaService.buscaCampanhasValidas();
		}

		return campanhasValidas;
	}

	public void associaUsuarioCampanhas(String usuarioId, List<String> campanhasId) {
		validador.validaUsuarioId(usuarioId);

		Usuario usuario = repository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

		List<Campanha> campanhas = campanhaService.buscaPorIds(campanhasId);

		Set<Campanha> campanhasSet = new HashSet<>();
		campanhasSet.addAll(usuario.getCampanhas());
		campanhasSet.addAll(campanhas);

		usuario.setCampanhas(new ArrayList<Campanha>(campanhasSet));

		repository.save(usuario);
	}

	public Usuario buscaPorId(String usuarioId) {
		validador.validaUsuarioId(usuarioId);

		return repository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
	}
}
