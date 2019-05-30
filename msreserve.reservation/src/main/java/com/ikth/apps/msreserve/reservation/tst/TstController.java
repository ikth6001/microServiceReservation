package com.ikth.apps.msreserve.reservation.tst;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TstController {

	@Value("${sample.value}")
	private String value;
	
	@GetMapping("/config")
	public ResponseEntity<String> getSampleValue(Model model) {
		return new ResponseEntity<String>(value, HttpStatus.OK);
	}
}
