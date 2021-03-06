package br.com.prova.campanha.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampanhaDTORequest {

	private String id;

	@NotEmpty
	private String nome;

	private String timeCoracaoId;

	@NotNull
	private LocalDateTime dataInicio;

	@NotNull
	private LocalDateTime dataTermino;

	private String status;
}
