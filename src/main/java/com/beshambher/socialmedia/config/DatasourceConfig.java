package com.beshambher.socialmedia.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "DATABASE_DIALECT", havingValue = "org.hibernate.dialect.PostgreSQLDialect", matchIfMissing = false)
public class DatasourceConfig {

	@Value("${DATABASE_URL:url}")
	private String envDBUrl;

	@Bean
	public DataSource dataSource() throws URISyntaxException {

		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		URI dbUri = new URI(envDBUrl);
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);

		return dataSourceBuilder.build();
	}

}
