package br.com.prova.campanha.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoDTOResponse {

	private String id;
	private LocalDate data;
	private List<String> campanhasAlteradas;
}
