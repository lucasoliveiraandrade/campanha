package br.com.prova.campanha.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Historico;
import br.com.prova.campanha.dto.response.HistoricoDTOResponse;
import br.com.prova.campanha.util.DataUtil;

@Component
public class HistoricoMapper {

	@Autowired
	private DataUtil dataUtil;

	public HistoricoDTOResponse toDTO(Historico historico) {
		String data = dataUtil.converteLocalDateParaString(historico.getData());

		return HistoricoDTOResponse.builder().id(historico.getId()).data(data)
				.campanhasAlteradas(historico.getCampanhasAlteradas()).build();
	}
}
