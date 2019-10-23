package com.ikth.apps.msreserve.zuul.proxy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.ikth.apps.msreserve.zuul.proxy.customs.MutableHeaderHttpServletRequest;

public class TokenUserCustmoHeaderRegistFilter implements Filter {

	private Logger logger= LoggerFactory.getLogger(TokenUserCustmoHeaderRegistFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/**
		 * ignore
		 */
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response
						, FilterChain chain) throws IOException, ServletException {
		
		Authentication authResult= SecurityContextHolder.getContext().getAuthentication();
		
		if(authResult == null
				|| !authResult.isAuthenticated()) {
			logger.debug("This request [{}] is on annonymouse authentication", "uri??");
		} else {
			
			if(authResult instanceof OAuth2Authentication) {
				OAuth2Authentication oauth2Auth= (OAuth2Authentication) authResult;
				String userName= (String) oauth2Auth.getPrincipal();
				logger.debug("Detected authorization user name is [{}]", userName);
				
				if(request instanceof HttpServletRequest) {
					HttpServletRequest httpReq= (HttpServletRequest) request;
					MutableHeaderHttpServletRequest mutableHttpReq= new MutableHeaderHttpServletRequest(httpReq);
					mutableHttpReq.addHeader("cust-username", userName);
					chain.doFilter(mutableHttpReq, response);
					
					return;
				}
			} else {
				logger.warn("Invalid authentication is detected. it is ignored");
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		/**
		 * ignore
		 */
	}

}
