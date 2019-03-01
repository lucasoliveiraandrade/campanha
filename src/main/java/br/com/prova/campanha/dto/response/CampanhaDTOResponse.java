package br.com.prova.campanha.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampanhaDTOResponse {

	private String id;
	private String nome;
	private String timeCoracao;
	private String dataInicio;
	private String dataTermino;
}
