package br.com.prova.campanha.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.collection.Campanha.CampanhaBuilder;
import br.com.prova.campanha.dto.request.CampanhaDTORequest;
import br.com.prova.campanha.dto.response.CampanhaDTOResponse;
import br.com.prova.campanha.enumeration.StatusCampanha;

@Component
public class CampanhaMapper extends BaseMapper {

	public Campanha toObject(CampanhaDTORequest dtoRequest) {
		LocalDate dataInicio = LocalDate.parse(dtoRequest.getDataInicio(), dataFormatter);
		LocalDate dataTermino = LocalDate.parse(dtoRequest.getDataTermino(), dataFormatter);

		CampanhaBuilder builder = Campanha.builder().id(dtoRequest.getId()).nome(dtoRequest.getNome())
				.timeCoracaoId(dtoRequest.getTimeCoracaoId()).dataInicio(dataInicio).dataTermino(dataTermino);

		if (!StringUtils.isBlank(dtoRequest.getStatus())) {
			builder.status(StatusCampanha.valueOf(dtoRequest.getStatus()));
		}

		return builder.build();
	}

	public CampanhaDTOResponse toDTO(Campanha campanha) {
		String dataInicio = converteLocalDateParaString(campanha.getDataInicio());
		String dataTermino = converteLocalDateParaString(campanha.getDataTermino());

		return CampanhaDTOResponse.builder().id(campanha.getId()).nome(campanha.getNome())
				.timeCoracaoId(campanha.getTimeCoracaoId()).dataInicio(dataInicio).dataTermino(dataTermino)
				.status(campanha.getStatus().name()).build();

	}

	public List<CampanhaDTOResponse> toDTOs(List<Campanha> campanhas) {
		return campanhas.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
