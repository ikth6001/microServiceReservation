package com.ikth.apps.msreserve.reservation.tst;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleErrorController implements ErrorController {

	@RequestMapping(value="/error")
	public String errorHandling(HttpServletRequest req) {
		return "error";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

	
}
