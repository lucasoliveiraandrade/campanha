package br.com.prova.campanha.integrationtest.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampanhaControllerIntegrationTest {

	private static final String URL_PADRAO = "/api/v1/campanhas/";
	private static final String CONTEUDO_POST = "{ \"nome\": \"campanha teste\", \"timeCoracaoId\": \"1\", \"dataInicio\": \"2019-03-20T01:44:50\", \"dataTermino\": \"2019-03-23T01:44:50\"	}";
	private static final String CONTEUDO_PUT = "{ \"id\":\"%s\", \"nome\": \"campanha teste 2\", \"timeCoracaoId\": \"2\", \"dataInicio\": \"2019-04-20T01:44:50\", \"dataTermino\": \"2019-04-23T01:44:50\" }";

	private static String campanhaId;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * GIVEN a aplicação está em correto funcionamento 
	 * WHEN uma requisição de criação de uma campanha é feita 
	 * THEN uma nova campanha deve ser criada corretamente
	 */
	@Test
	public void teste001_deveCadastrarNovaCampanha() throws Exception {
		campanhaId = mockMvc.perform(post(URL_PADRAO).content(CONTEUDO_POST).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
	}

	/**
	 * GIVEN uma campanha foi previamente criada 
	 * WHEN uma busca é feita pelo seu identificador 
	 * THEN a campanha é retornada com sucesso
	 */
	@Test
	public void teste002_deveBuscarCampanhaPorId() throws Exception {
		String resposta = mockMvc.perform(get(URL_PADRAO + campanhaId).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
		assertTrue(resposta.contains(campanhaId));
	}

	/**
	 * GIVEN a aplicação está em correto funcionamento 
	 * WHEN uma busca por todas as campnhas é realizada 
	 * THEN todas as campanhas ativas devem ser restornadas com sucesso
	 */
	@Test
	public void teste003_deveBuscarTodasCampanhas() throws Exception {
		String resposta = mockMvc.perform(get(URL_PADRAO).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
		assertTrue(resposta.contains(campanhaId));
	}

	/**
	 * GIVEN uma campanha foi previamente criada 
	 * WHEN uma requisição de alteração para esta campanha é feita 
	 * THEN a campanha deve ser alterada corretamente
	 */
	@Test
	public void teste004_deveAlterarCampanhaExistente() throws Exception {
		String conteudo = String.format(CONTEUDO_PUT, campanhaId);

		String resposta = mockMvc.perform(put(URL_PADRAO).contentType(APPLICATION_JSON).content(conteudo))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
		assertTrue(resposta.contains(campanhaId));

		resposta = mockMvc.perform(get(URL_PADRAO + campanhaId).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
		assertTrue(resposta.contains(campanhaId));
		assertTrue(resposta.contains("campanha teste 2"));
		assertTrue(resposta.contains("2019-04-20T01:44:50"));
		assertTrue(resposta.contains("2019-04-20T01:44:50"));
	}

	/**
	 * GIVEN uma campanha foi previamente criada 
	 * WHEN uma requisição de exclusão para esta campanha é feita 
	 * THEN a campanha deve ser excluída corretamente
	 */
	@Test(expected = Exception.class)
	public void teste005_deveExcluirCampanha() throws Exception {
		String resposta = mockMvc.perform(delete(URL_PADRAO + campanhaId).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertNotNull(resposta);
		assertTrue(resposta.equals(campanhaId));

		resposta = mockMvc.perform(get(URL_PADRAO + campanhaId).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}
}
