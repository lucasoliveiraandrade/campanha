package br.com.prova.campanha.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class BaseMapper {

	protected DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	protected String converteLocalDateParaString(LocalDate date) {
		return String.format("%s/%s/%s", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
	}
}
