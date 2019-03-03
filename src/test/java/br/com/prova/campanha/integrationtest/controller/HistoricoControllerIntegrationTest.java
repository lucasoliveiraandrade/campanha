package br.com.prova.campanha.integrationtest.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = br.com.prova.campanha.Aplicacao.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
public class HistoricoControllerIntegrationTest {

	private static final String CONTEUDO_POST = "{ \"nome\": \"campanha teste\", \"timeCoracaoId\": \"1\", \"dataInicio\": \"20/03/2019\", \"dataTermino\": \"23/03/2019\"	}";

	@Autowired
	private MockMvc mockMvc;

	/**
	 * GIVEN a aplicação está em correto funcionamento
	 * WHEN uma requisição de busca de histórico é feita
	 * THEN um histórico deve ser retornado corretamente de acordo com a data requerida
	 */
	@Test
	public void deveBuscarHistoricoPorData() throws Exception {
		mockMvc.perform(post("/prova/campanhas").content(CONTEUDO_POST).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated());

		mockMvc.perform(post("/prova/campanhas").content(CONTEUDO_POST).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated());

		String resposta = mockMvc.perform(get(criaURL()).contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
	}

	private String criaURL() {
		LocalDate now = LocalDate.now();

		int diaInt = now.getDayOfMonth();
		String dia = diaInt < 10 ? "0" + diaInt : "" + diaInt;

		int mesInt = now.getMonthValue();
		String mes = mesInt < 10 ? "0" + mesInt : "" + mesInt;

		return String.format("/prova/historicos?data=%s/%s/%s", dia, mes, now.getYear());
	}
}