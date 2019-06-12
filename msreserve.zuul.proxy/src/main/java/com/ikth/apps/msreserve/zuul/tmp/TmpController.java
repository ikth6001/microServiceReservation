package com.ikth.apps.msreserve.zuul.tmp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TmpController {

	@GetMapping("/auth")
	public ResponseEntity<String> getSampleValue() {
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
