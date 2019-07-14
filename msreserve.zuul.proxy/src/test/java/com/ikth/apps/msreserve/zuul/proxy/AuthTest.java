package com.ikth.apps.msreserve.zuul.proxy;

import java.util.Base64;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthTest {

	@Test
	public void test() {
		
//		RestTemplate restTemplate= new RestTemplate();
//		final String url= "http://192.168.99.100:8080/api/reserve/api/test/param";
//		
//		HttpHeaders headers= new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		byte[] user= Base64.getEncoder().encode("user:user".getBytes());
//		headers.set("Authorization", "Basic " + new String(user));
//		
//		HttpEntity<?> entity= new HttpEntity<>(headers);
//		
//		ResponseEntity<String> res= restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new Object[] {});
//		String resultString= res.getBody();
//		System.out.println("######################################################################");
//        System.out.println(resultString);
//		System.out.println("######################################################################");
	}

}
