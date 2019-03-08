package br.com.prova.campanha.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampanhaDTOResponse {

	private String id;
	private String nome;
	private String timeCoracaoId;
	private LocalDateTime dataInicio;
	private LocalDateTime dataTermino;
	private String status;
}
