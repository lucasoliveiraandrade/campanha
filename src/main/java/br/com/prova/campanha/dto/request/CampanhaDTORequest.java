package br.com.prova.campanha.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampanhaDTORequest {

	private String id;

	@NotEmpty
	private String nome;
	private String timeCoracaoId;

	@NotEmpty
	private String dataInicio;

	@NotEmpty
	private String dataTermino;
	private String status;
}
