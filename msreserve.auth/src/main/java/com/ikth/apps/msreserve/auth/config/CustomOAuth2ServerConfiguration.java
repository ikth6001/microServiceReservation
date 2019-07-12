package com.ikth.apps.msreserve.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class CustomOAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.resource.jwt.key-value:com.ikth.apps}")
	private String accessKey;
	
	/**
	 * 타 어플리케이션에 API 제공할 목적이 아니므로..
	 */
	private final String CLIENT_ID= "foo";
	private final String CLIENT_SECRET= "{noop}bar";
	
	@Autowired
	private AuthenticationManager authenticationMangaer;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		super.configure(endpoints);
		endpoints.accessTokenConverter(accessTokenConverter())
				 .authenticationManager(authenticationMangaer);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			   .withClient(CLIENT_ID)
			   .secret(CLIENT_SECRET)
			   .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
			   .scopes("read", "write");
		
//		필요 없음
//		clients.withClientDetails(new JdbcClientDetailsService(dataSource));
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		super.configure(security);
		security.checkTokenAccess("permitAll()");
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
