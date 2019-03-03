package br.com.prova.campanha.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prova.campanha.validacao.DataValidacao;

@Component
public class DataUtil {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	@Autowired
	private DataValidacao validador;

	public String converteLocalDateParaString(LocalDate data) {
		validador.validaExistenciaData(data);
		return String.format("%s/%s/%s", data.getDayOfMonth(), data.getMonthValue(), data.getYear());
	}

	public LocalDate converteStringParaLocalDate(String data) {
		validador.validaExistenciaData(data);
		return LocalDate.parse(data, DateTimeFormatter.ofPattern(DD_MM_YYYY));
	}

	public DateTimeFormatter retornaFormatoDataPadrao() {
		return DateTimeFormatter.ofPattern(DD_MM_YYYY);
	}
}
