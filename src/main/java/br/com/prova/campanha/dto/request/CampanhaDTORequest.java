package br.com.prova.campanha.dto.request;

import lombok.Data;

@Data
public class CampanhaDTORequest {

	private String id;
	private String nome;
	private String timeCoracaoId;
	private String dataInicio;
	private String dataTermino;
	private String status;
}
