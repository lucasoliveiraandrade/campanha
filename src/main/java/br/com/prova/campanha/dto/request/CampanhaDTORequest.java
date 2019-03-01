package br.com.prova.campanha.dto.request;

import lombok.Data;

@Data
public class CampanhaDTORequest {

	private String nome;
	private String tipoCoracao;
	private String dataInicio;
	private String dataTermino;
}
