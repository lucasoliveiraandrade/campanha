package br.com.prova.campanha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.prova.campanha" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Aplicacao {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}
}