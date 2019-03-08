package br.com.prova.campanha.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.prova.campanha.enumeration.StatusCampanha;
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
	private String timeCoracaoId;

	@NotNull
	private LocalDateTime dataInicio;

	@NotNull
	private LocalDateTime dataTermino;

	@NotNull
	private StatusCampanha status;
}
