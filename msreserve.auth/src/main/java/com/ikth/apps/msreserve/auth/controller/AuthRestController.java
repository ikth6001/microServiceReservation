package com.ikth.apps.msreserve.auth.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
	
	@SuppressWarnings("unused")
	private Logger logger= LoggerFactory.getLogger(AuthRestController.class);

	@RequestMapping(value= "/logout"
					, method= RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse res) {
		
		Authentication authResult= SecurityContextHolder.getContext().getAuthentication();
		
		if(authResult == null
				|| !authResult.isAuthenticated()
				|| !hasBearer(req.getCookies())) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			
//			Cookie[] cookies= req.getCookies();
//			
//			/**
//			 * annonymouse user..
//			 */
//			if(cookies == null
//					|| cookies.length < 1) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//			}
//			
//			for(Cookie cookie : cookies) {
//				if("Bearer".equals(cookie.getName())) {
//					cookie.setMaxAge(-1);
//					cookie.setValue(null);
//					res.addCookie(cookie);
//				}
//			}
//			
//			try {
//				req.logout();
//			} catch (ServletException e) {
//				logger.error(e.getMessage(), e);
//			}
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
	}
	
	private boolean hasBearer(Cookie[] cookies) {
		if(cookies == null
				|| cookies.length < 1) {
			return false;
		}
		
		for(Cookie cookie : cookies) {
			if("Bearer".equals(cookie.getName())) {
				return StringUtils.hasText(cookie.getValue());
			}
		}
		
		return false;
	}
}
