package com.beshambher.socialmedia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Value("${swagger.author.name}")
	private String name;

	@Value("${swagger.author.email}")
	private String email;

	@Value("${swagger.author.url}")
	private String contactUrl;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Spring Boot REST API")
						.description("Spring Boot REST API for Social Media")
						.version("1.0")
						.termsOfService("/terms")
						.license(new License().name("Apache License Version 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0"))
						.contact(new Contact().name(name).email(email).url(contactUrl)));
	}
}
