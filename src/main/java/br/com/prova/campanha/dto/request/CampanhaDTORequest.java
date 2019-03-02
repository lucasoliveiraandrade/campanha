package br.com.prova.campanha.dto.request;

import lombok.Data;

@Data
public class CampanhaDTORequest {

	private String id;
	private String nome;
	private String timeCoracao;
	private String dataInicio;
	private String dataTermino;
}
