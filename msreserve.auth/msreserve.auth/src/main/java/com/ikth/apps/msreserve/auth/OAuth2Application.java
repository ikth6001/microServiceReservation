package com.ikth.apps.msreserve.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuth2Application {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2Application.class, args);
	}
	
//	@Bean
//	public TokenStore jdbcTokenStore(DataSource dataSource) {
//		return new JdbcTokenStore(dataSource);
//	}
}
