package br.com.wiliansanello.gestao_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SpringBootApplication
public class GestaoVagasApplication {

	@SecurityRequirement(name = "jwt_auth")
	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}

}
