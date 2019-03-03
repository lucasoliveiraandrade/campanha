package br.com.prova.campanha.mapper;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Usuario;
import br.com.prova.campanha.dto.request.UsuarioDTORequest;
import br.com.prova.campanha.dto.response.UsuarioDTOResponse;
import br.com.prova.campanha.service.CampanhaService;
import br.com.prova.campanha.util.DataUtil;

@Component
public class UsuarioMapper {

	@Autowired
	private DataUtil dataUtil;

	@Autowired
	private CampanhaService campanhaService;

	public Usuario toObject(UsuarioDTORequest dtoRequest) {
		LocalDate dataNascimento = dataUtil.converteStringParaLocalDate(dtoRequest.getDataNascimento());

		List<Campanha> campanhas = campanhaService.buscaPorIds(dtoRequest.getCampanhas());

		return Usuario.builder().nome(dtoRequest.getNome()).email(dtoRequest.getEmail())
				.timeCoracaoId(dtoRequest.getTimeCoracaoId()).dataNascimento(dataNascimento).campanhas(campanhas)
				.build();

	}

	public UsuarioDTOResponse toDTO(Usuario usuario) {
		String dataNascimento = dataUtil.converteLocalDateParaString(usuario.getDataNascimento());

		List<String> campanhas = new ArrayList<>();

		if (!isEmpty(usuario.getCampanhas())) {
			campanhas = usuario.getCampanhas().stream().map(c -> c.getId()).collect(Collectors.toList());
		}

		return UsuarioDTOResponse.builder().id(usuario.getId()).nome(usuario.getNome()).dataNascimento(dataNascimento)
				.campanhas(campanhas).email(usuario.getEmail()).timeCoracaoId(usuario.getTimeCoracaoId()).build();
	}

	public List<UsuarioDTOResponse> toDTOs(List<Usuario> usuarios) {
		return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
