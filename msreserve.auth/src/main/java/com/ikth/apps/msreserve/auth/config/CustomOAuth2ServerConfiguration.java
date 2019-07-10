package com.ikth.apps.msreserve.auth.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class CustomOAuth2ServerConfiguration extends OAuth2AuthorizationServerConfiguration {

	/**
	 * 타 어플리케이션에 API 제공할 목적이 아니므로..
	 */
	private final String CLIENT_ID= "foo";
	private final String CLIENT_SECRET= "bar";
	
	private @Autowired AccessTokenConverter accessTokenConverter;
	
	public CustomOAuth2ServerConfiguration(BaseClientDetails details, AuthenticationConfiguration authenticationConfiguration,
			ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter,
			AuthorizationServerProperties properties) throws Exception {
		super(details, authenticationConfiguration, tokenStore, tokenConverter, properties);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		super.configure(endpoints);
		endpoints.accessTokenConverter(accessTokenConverter);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		super.configure(security);
		security.checkTokenAccess("isAuthenticated()");
		security.tokenKeyAccess("isAuthenticated()");
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
}
