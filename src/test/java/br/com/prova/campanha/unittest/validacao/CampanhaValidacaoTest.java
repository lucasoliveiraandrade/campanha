package br.com.prova.campanha.unittest.validacao;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.prova.campanha.model.Campanha;
import br.com.prova.campanha.validacao.CampanhaValidacao;

@RunWith(MockitoJUnitRunner.class)
public class CampanhaValidacaoTest {

	@InjectMocks
	CampanhaValidacao validador;

	@Test(expected = Exception.class)
	public void deveLancarExceptionSeCampanhaIdNull() {
		validador.validaCampanhaId(null);
	}

	@Test
	public void naoDeveLancarExceptionSeCampanhaIdNaoNull() {
		validador.validaCampanhaId("123");
	}

	@Test(expected = Exception.class)
	public void deveLancarExceptionSeCampanhaComVigenciaVencida() {
		Campanha campanha = Campanha.builder().dataTermino(LocalDateTime.now().minusMonths(1)).build();
		validador.validaCampanhaComVigenciaVencida(campanha);
	}

	@Test
	public void naoDeveLancarExceptionSeCampanhaComVigenciaValida() {
		Campanha campanha = Campanha.builder().dataTermino(LocalDateTime.now().plusMonths(1)).build();
		validador.validaCampanhaComVigenciaVencida(campanha);
	}
}
