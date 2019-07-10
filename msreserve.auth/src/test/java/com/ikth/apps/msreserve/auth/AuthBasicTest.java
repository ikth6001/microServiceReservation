package com.ikth.apps.msreserve.auth;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= OAuth2Application.class)
@WebAppConfiguration
public class AuthBasicTest {

	private MockMvc mockMvc;
	
	private final String CLIENT_ID= "foo";
	private final String CLIENT_SECRET= "bar";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetJwtBaseAccessToken() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", CLIENT_ID);
	    params.add("username", "user01");
	    params.add("password", "user01");
	 
	    ResultActions result 
	      = mockMvc.perform(post("/oauth/token")
	        .params(params)
	        .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
	        .accept("application/json;charset=UTF-8"))
	        .andExpect(status().isOk());
//	        .andExpect(content().contentType("application/json;charset=UTF-8"));
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    final String token= jsonParser.parseMap(resultString).get("access_token").toString();
	    System.out.println(token);
	}
}
