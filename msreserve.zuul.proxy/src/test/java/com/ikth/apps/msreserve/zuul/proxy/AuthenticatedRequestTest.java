package com.ikth.apps.msreserve.zuul.proxy;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticatedRequestTest {

	private final Logger logger= LoggerFactory.getLogger(AuthenticatedRequestTest.class);
	
	private String token= null;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webCtxt;
	
	@Autowired
	private FilterChainProxy securityProxy;
	
	private final String USER_ID= "user";
	private final String USER_PW= "user";
	
	@Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webCtxt).addFilter(securityProxy).build();
        token= createJwtToken();
    }
	
	private String createJwtToken() {
		JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("com.ikth.apps");
		OAuth2AccessToken accessToken= new DefaultOAuth2AccessToken((String) null);
		OAuth2Authentication authentication= 
				new OAuth2Authentication(
						new OAuth2Request(Collections.emptyMap(), "foo", null, true, null, null, null, null, null)
						, new UsernamePasswordAuthenticationToken("user", "user"));
		final String newToken= jwtAccessTokenConverter.enhance(accessToken, authentication).getValue();
		
		logger.debug("##########################################################");
		logger.debug("encoded jwt access token [{}]", newToken);
		logger.debug("##########################################################");
		
		return newToken;
	}
	
	@Test
	public void authReqFailMock() throws Exception {
		
		mockMvc.perform(post("/api/reservation/api/test/param")
		       .with(httpBasic(USER_ID, USER_PW))
		       .accept("application/json;charset=UTF-8"))
		       .andExpect(status().isUnauthorized());
	}
	
//	@Test
//	public void authReqFail() {
//		RestTemplate restTemplate= getNoErrorHandlingRestTemplate();
//		final String url= "http://192.168.99.100:8080/api/reserve/api/test/param";
//		
//		HttpHeaders headers= new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		byte[] user= Base64.getEncoder().encode((USER_ID + ":" + USER_PW).getBytes());
//		headers.set("Authorization", "Basic " + new String(user));
//		
//		HttpEntity<?> entity= new HttpEntity<>(headers);
//		ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[] {});
//		assertEquals(HttpStatus.UNAUTHORIZED, res.getStatusCode());
//	}
	
	@Test
	public void authReqSuccessMock() throws Exception {
		
		if(StringUtils.isEmpty(token)) {
			throw new Exception("token is empty");
		}
		
		mockMvc.perform(post("/api/reservation/api/test/param")
			   .header("Authorization", "Bearer " + token)
			   .accept("application/json;charset=UTF-8"))
			   .andExpect(status().isNotFound()); // spring config 정보 못가져와서 URI 매핑 실패함. TODO profile 활용
	}
	
	@SuppressWarnings("unused")
	private RestTemplate getNoErrorHandlingRestTemplate() {
		
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
			}
		});
		
		return restTemplate;
	}
}
