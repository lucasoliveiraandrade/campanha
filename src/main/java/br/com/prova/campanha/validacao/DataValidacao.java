package br.com.prova.campanha.validacao;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DataValidacao {

	public void validaExistenciaData(LocalDate data) {
		if (data == null) {
			throw new RuntimeException("Data inválida.");
		}
	}

	public void validaExistenciaData(String data) {
		if (StringUtils.isBlank(data)) {
			throw new RuntimeException("Data inválida.");
		}
	}
}
