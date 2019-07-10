package com.ikth.apps.msreserve.reservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTestController {

	private final static Logger logger= LoggerFactory.getLogger(SimpleTestController.class);
	
	@RequestMapping(
			value= "/api/test/{parameter}"
			, method= RequestMethod.GET)
	public ResponseEntity<String> test(@PathVariable("parameter") String parameter) {
		logger.debug("requested parameter [{}]", parameter);
		return new ResponseEntity<String>(parameter, HttpStatus.OK);
	}
}