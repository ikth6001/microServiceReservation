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
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.ikth.apps.msreserve.zuul.proxy.customs.CookieBaseBearerTokenExtractor;
import com.ikth.apps.msreserve.zuul.proxy.filter.TokenUserCustmoHeaderRegistFilter;

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
			.antMatchers(permitAllUri).permitAll()
			.and()
//			.cors();
			.addFilterAfter(new TokenUserCustmoHeaderRegistFilter(), AbstractPreAuthenticatedProcessingFilter.class);
	}
	
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.addAllowedOrigin("*");
//		configuration.addAllowedMethod("*");
//		configuration.addAllowedHeader("*");
//		configuration.setAllowCredentials(true);
//		configuration.setMaxAge(3600L);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore())
				 .tokenExtractor(new CookieBaseBearerTokenExtractor());
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
