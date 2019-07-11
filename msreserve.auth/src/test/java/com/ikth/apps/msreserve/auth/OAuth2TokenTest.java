package com.ikth.apps.msreserve.auth;

import java.util.Base64;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
public class OAuth2TokenTest {
	
	private String SCOPE = "read";
    private String CLIENT_ID = "foo";
    private String CLIENT_SECRET = "bar";
    private String SECURITY_USERNAME = "user";
    private String SECURITY_PASSWORD = "user";
    
//    private MockMvc mockMvc;
//	
//	@Autowired
//	private WebApplicationContext webCtxt;
//	
//	@Autowired
//	private FilterChainProxy securityProxy;
//	
//	@Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(this.webCtxt).addFilter(securityProxy).build();
//    }
//
//	@Test
//	public void getAccessTokenMock() {
//		
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//	    params.add("grant_type", "password");
//	    params.add("client_id", CLIENT_ID); //foo
//	    params.add("username", SECURITY_USERNAME);
//	    params.add("password", SECURITY_PASSWORD);
//	    params.add("scope", SCOPE); //read
//
//	    ResultActions result
//	            = mockMvc.perform(post("/oauth/token")
//	            .params(params)
//	            .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
//	            .accept("application/json;charset=UTF-8"))
//	            .andExpect(status().isOk())
//	            .andExpect(content().contentType("application/json;charset=UTF-8"));
//
//	    String resultString = result.andReturn().getResponse().getContentAsString();
//
//	    JacksonJsonParser jsonParser = new JacksonJsonParser();
//	    final String token= jsonParser.parseMap(resultString).get("access_token").toString();
//	    System.out.println("#########################################");
//	    System.out.println(token);
//	    System.out.println("#########################################");
//	}
	
	@Test
	public void getAccessTokenReal() {
		
		RestTemplate restTemplate= new RestTemplate();
		final String url= "http://192.168.99.100:8763/oauth/token?grant_type=password&client_id=" + CLIENT_ID + "&username=" + SECURITY_USERNAME + "&password=" + SECURITY_PASSWORD + "&scope=" + SCOPE;
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		byte[] encodedUser= Base64.getEncoder().encode((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
		headers.set("Authorization", "Basic " + new String(encodedUser));
		
		HttpEntity<?> entity= new HttpEntity<>(headers);
		
		ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.POST, entity, String.class, new Object[] {});
		String resultString= res.getBody();
		
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		Map<String, Object> parsedJson= jsonParser.parseMap(resultString);
		String token= (String) parsedJson.get("access_token");
		System.out.println("######################################################################");
        System.out.println(parsedJson.toString());
		System.out.println(token);
		System.out.println("######################################################################");
	}

}
