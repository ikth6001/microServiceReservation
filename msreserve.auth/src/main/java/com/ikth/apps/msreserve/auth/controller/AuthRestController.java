package com.ikth.apps.msreserve.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

	@RequestMapping(value= "/login"
					, method= RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam(value= "id") String id, @RequestParam(value= "passwd") String passwd) {
		/**
		 * TODO
		 * 유저 데이터 검증..
		 */
		if("user@gmail.com".equals(id)
				&& "user".equals(passwd)) {
			return new ResponseEntity<>(null, HttpStatus.MOVED_PERMANENTLY);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
}
