package br.com.prova.campanha.mapper;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.dto.request.CampanhaDTORequest;
import br.com.prova.campanha.dto.response.CampanhaDTOResponse;
import br.com.prova.campanha.enumeration.StatusCampanha;
import br.com.prova.campanha.model.Campanha;
import br.com.prova.campanha.model.Campanha.CampanhaBuilder;

@Component
public class CampanhaMapper {

	public Campanha toObject(CampanhaDTORequest campanhaDTORequest) {
		CampanhaBuilder builder = Campanha
				.builder()
				.id(campanhaDTORequest.getId())
				.nome(campanhaDTORequest.getNome())
				.timeCoracaoId(campanhaDTORequest.getTimeCoracaoId())
				.dataInicio(campanhaDTORequest.getDataInicio())
				.dataTermino(campanhaDTORequest.getDataTermino());

		if (isNotBlank(campanhaDTORequest.getStatus())) {
			builder.status(StatusCampanha.valueOf(campanhaDTORequest.getStatus()));
		}

		return builder.build();
	}

	public CampanhaDTOResponse toDTO(Campanha campanha) {
		return CampanhaDTOResponse
				.builder()
				.id(campanha.getId())
				.nome(campanha.getNome())
				.timeCoracaoId(campanha.getTimeCoracaoId())
				.dataInicio(campanha.getDataInicio())
				.dataTermino(campanha.getDataTermino())
				.status(campanha.getStatus().name())
				.build();
	}

	public List<CampanhaDTOResponse> toDTOs(List<Campanha> campanhas) {
		return campanhas.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
