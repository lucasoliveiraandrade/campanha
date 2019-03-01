package br.com.prova.campanha.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTOResponse {

	private String id;
	private String nome;
	private String tipo;
}
