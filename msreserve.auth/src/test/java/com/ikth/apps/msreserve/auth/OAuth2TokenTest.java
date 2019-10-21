package com.ikth.apps.msreserve.auth;

import java.util.Base64;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OAuth2TokenTest {
	
	private String SCOPE = "read";
    private String CLIENT_ID = "foo";
    private String CLIENT_SECRET = "bar";
    private String SECURITY_USERNAME = "user";
    private String SECURITY_PASSWORD = "user";
    
//	private MockMvc mockMvc;
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
//	public void getAccessTokenMock() throws Exception {
//		
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//	    params.add("grant_type", "password");
//	    params.add("client_id", CLIENT_ID);
//	    params.add("username", SECURITY_USERNAME);
//	    params.add("password", SECURITY_PASSWORD);
//	    params.add("scope", SCOPE);
//
//	    ResultActions result
//	            = mockMvc.perform(post("/oauth/token")
//	            .params(params)
//	            .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
//	            .accept("application/json;charset=UTF-8"))
//	            .andExpect(status().isOk());
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
	public void getAccessToken() {
		
		RestTemplate restTemplate= new RestTemplate();
//		final String url= "http://192.168.99.100:8763/oauth/token?grant_type=password&client_id=" + CLIENT_ID + "&username=" + SECURITY_USERNAME + "&password=" + SECURITY_PASSWORD + "&scope=" + SCOPE;
		final String url= "http://localhost:8763/oauth/token?grant_type=password&username=" + SECURITY_USERNAME + "&password=" + SECURITY_PASSWORD + "&scope=" + SCOPE;
//		final String url= "http://192.168.99.100:8763/oauth/token?grant_type=password&scope=" + SCOPE;
//		final String url= "http://localhost:8763/oauth/token?grant_type=password&scope=" + SCOPE;
		
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		byte[] user= Base64.getEncoder().encode((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
		headers.set("Authorization", "Basic " + new String(user));
		
		HttpEntity<?> entity= new HttpEntity<>(headers);
		
//		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//		body.add("username", SECURITY_USERNAME);
//		body.add("password", SECURITY_PASSWORD);
//		
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		
//		ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.POST, entity, String.class, new Object[] {});
		ResponseEntity<String> res= restTemplate.postForEntity(url, entity, String.class);
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
