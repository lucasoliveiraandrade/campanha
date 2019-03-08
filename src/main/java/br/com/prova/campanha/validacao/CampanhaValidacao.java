package br.com.prova.campanha.validacao;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.model.Campanha;

@Component
public class CampanhaValidacao {

	public void validaCampanhaId(String campanhaId) {
		if (campanhaId == null) {
			throw new RuntimeException("Identificador da campanha obrigatório.");
		}
	}

	public void validaCampanhaComVigenciaVencida(Campanha campanha) {
		if (campanha.getDataTermino().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Campanha com data de vigência vencida.");
		}
	}

	public void validaExistenciaCampanha(boolean existeCampanha) {
		if (!existeCampanha) {
			throw new RuntimeException("Campanha não encontrada.");
		}
	}
}
