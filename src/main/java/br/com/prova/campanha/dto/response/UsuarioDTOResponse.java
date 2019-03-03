package br.com.prova.campanha.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTOResponse {

	private String id;
	private String nome;
	private String email;
	private String timeCoracaoId;
	private String dataNascimento;
	private String tipo;
	private List<String> campanhas;
}
