package br.com.prova.campanha.collection;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.prova.campanha.enumeration.TipoUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "usuario")
public class Usuario {

	@Id
	private String id;

	@NotEmpty
	private String nome;

	private TipoUsuario tipo;
}
