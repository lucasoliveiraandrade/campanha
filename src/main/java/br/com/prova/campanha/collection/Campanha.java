package br.com.prova.campanha.collection;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "campanha")
public class Campanha {

	@Id
	private String id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String timeCoracao;

	@NotEmpty
	private LocalDate dataInicio;

	@NotEmpty
	private LocalDate dataTermino;

}
