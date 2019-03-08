package br.com.prova.campanha.mapper;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.dto.response.HistoricoDTOResponse;
import br.com.prova.campanha.model.Historico;

@Component
public class HistoricoMapper {

	public HistoricoDTOResponse toDTO(Historico historico) {
		return HistoricoDTOResponse
				.builder()
				.id(historico.getId())
				.data(historico.getData())
				.campanhasAlteradas(historico.getCampanhasAlteradas())
				.build();
	}
}
