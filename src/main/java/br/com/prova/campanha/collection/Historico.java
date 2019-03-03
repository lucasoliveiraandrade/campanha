package br.com.prova.campanha.collection;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "historico")
@Builder
@Data
public class Historico {

	@Id
	private String id;

	@NotEmpty
	private LocalDate data;

	@NotEmpty
	private List<String> campanhasAlteradas;
}
