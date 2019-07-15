package com.ikth.apps.msreserve.zuul.proxy;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
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

	@SuppressWarnings("unused")
	private String accessToken= null;
	
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
        
        /**
         * TODO get access token
         */
    }
	
	@Test
	public void authReqFailMock() throws Exception {
		
		mockMvc.perform(post("/api/reserve/api/test/param")
		       .with(httpBasic(USER_ID, USER_PW))
		       .accept("application/json;charset=UTF-8"))
		       .andExpect(status().isUnauthorized());
	}
	
	@Test
	public void authReqFail() {
		RestTemplate restTemplate= getNoErrorHandlingRestTemplate();
		final String url= "http://192.168.99.100:8080/api/reserve/api/test/param";
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		byte[] user= Base64.getEncoder().encode((USER_ID + ":" + USER_PW).getBytes());
		headers.set("Authorization", "Basic " + new String(user));
		
		HttpEntity<?> entity= new HttpEntity<>(headers);
		ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[] {});
		assertEquals(HttpStatus.UNAUTHORIZED, res.getStatusCode());
	}
	
	@Test
	public void authReqSuccess() {
		
		/**
		 * TODO
		 */
	}
	
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
