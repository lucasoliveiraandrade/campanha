package br.com.prova.campanha.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Campanha;
import br.com.prova.campanha.dto.request.CampanhaDTORequest;
import br.com.prova.campanha.dto.response.CampanhaDTOResponse;

@Component
public class CampanhaMapper extends BaseMapper {

	public Campanha toObject(CampanhaDTORequest dtoRequest) {
		LocalDate dataInicio = LocalDate.parse(dtoRequest.getDataInicio(), dataFormatter);
		LocalDate dataTermino = LocalDate.parse(dtoRequest.getDataTermino(), dataFormatter);

		return Campanha.builder().nome(dtoRequest.getNome()).timeCoracao(dtoRequest.getTipoCoracao())
				.dataInicio(dataInicio).dataTermino(dataTermino).build();
	}

	public CampanhaDTOResponse toDTO(Campanha campanha) {
		String dataInicio = converteLocalDateParaString(campanha.getDataInicio());
		String dataTermino = converteLocalDateParaString(campanha.getDataTermino());

		return CampanhaDTOResponse.builder().id(campanha.getId()).nome(campanha.getNome())
				.timeCoracao(campanha.getTimeCoracao()).dataInicio(dataInicio).dataTermino(dataTermino).build();
	}

	public List<CampanhaDTOResponse> toDTOs(List<Campanha> campanhas) {
		return campanhas.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
