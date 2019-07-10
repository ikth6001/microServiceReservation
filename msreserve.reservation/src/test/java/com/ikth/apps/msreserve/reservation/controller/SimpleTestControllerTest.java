package com.ikth.apps.msreserve.reservation.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ikth.apps.msreserve.reservation.ReservationApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= ReservationApplication.class)
@WebAppConfiguration
public class SimpleTestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void test() throws Exception {
		final String PARAM= "hello";
		final String response= mockMvc.perform(get("/api/test/" + PARAM)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(PARAM, response);
	}

}
