package br.com.prova.campanha.validacao;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Campanha;

@Component
public class CampanhaValidacao {

	public void validaCampanhaId(String campanhaId) {
		if (campanhaId == null) {
			throw new RuntimeException("Identificador da campanha obrigatório.");
		}
	}

	public void validaCampanhaComVigenciaVencida(Campanha campanha) {
		if (campanha.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Campanha com data de vigência vencida.");
		}
	}

	public void validaCampanhaExistente(boolean existeCampanha) {
		if (!existeCampanha) {
			throw new RuntimeException("Campanha não encontrada.");
		}
	}
}
