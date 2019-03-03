package br.com.prova.campanha.mapper;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Historico;
import br.com.prova.campanha.dto.response.HistoricoDTOResponse;

@Component
public class HistoricoMapper extends BaseMapper {

	public HistoricoDTOResponse toDTO(Historico historico) {

		String data = converteLocalDateParaString(historico.getData());

		return HistoricoDTOResponse.builder().id(historico.getId()).data(data)
				.campanhasAlteradas(historico.getCampanhasAlteradas()).build();
	}
}
