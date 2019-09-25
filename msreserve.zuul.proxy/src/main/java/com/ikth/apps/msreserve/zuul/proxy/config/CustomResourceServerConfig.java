package com.ikth.apps.msreserve.zuul.proxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${security.oauth2.resource.jwt.key-value:com.ikth.apps}")
	private String accessKey;
	
	@Value("${security.uris.authenticated:/api/reservation/**,/web/reservation**}")
	private String[] authenticatedUri;
	
	@Value("${security.uris.permitAll:/**}")
	private String[] permitAllUri;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers(authenticatedUri).authenticated()
			.antMatchers(permitAllUri).permitAll();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter= new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(accessKey);
		return tokenConverter;
	}
}
