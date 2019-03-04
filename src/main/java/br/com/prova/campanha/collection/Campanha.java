package br.com.prova.campanha.collection;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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

	@NotEmpty
	private LocalDate dataInicio;

	@NotEmpty
	private LocalDate dataTermino;

	@NotEmpty
	private StatusCampanha status;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
