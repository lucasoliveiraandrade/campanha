package br.com.prova.campanha.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection = "historico")
public class Historico {

	@Id
	private String id;

	@NotNull
	private LocalDate data;

	@NotNull
	private List<String> campanhasAlteradas;
}
