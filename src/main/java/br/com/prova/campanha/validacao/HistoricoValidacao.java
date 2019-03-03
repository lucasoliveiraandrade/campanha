package br.com.prova.campanha.validacao;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.prova.campanha.collection.Historico;

@Component
public class HistoricoValidacao {

	public void validaDataHistorico(LocalDate data) {
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
