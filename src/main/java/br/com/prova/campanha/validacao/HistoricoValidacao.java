package br.com.prova.campanha.validacao;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.model.Historico;

@Component
public class HistoricoValidacao {

	public void validaDataHistorico(LocalDateTime data) {
		if (data == null) {
			throw new RuntimeException("Data inválida");
		}
	}

	public void validaHistorico(Historico historico) {
		if (historico == null) {
			throw new RuntimeException("Histórico não encontrado");
		}
	}
}
