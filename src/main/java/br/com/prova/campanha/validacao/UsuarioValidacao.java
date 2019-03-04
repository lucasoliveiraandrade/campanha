package br.com.prova.campanha.validacao;

import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacao {

	public void validaUsuarioJaExistente(boolean existeUsuario) {
		if (existeUsuario) {
			throw new RuntimeException("Usuário já existente.");
		}
	}

	public void validaUsuarioId(String usuarioId) {
		if (usuarioId == null) {
			throw new RuntimeException("Identificador do usuário obrigatório.");
		}
	}
}
