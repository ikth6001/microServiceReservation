package com.ikth.apps.msreserve.auth.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

@Deprecated
public class LoggableDispatcherServlet extends DispatcherServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602345812490708642L;
	
	private Logger logger= LoggerFactory.getLogger(LoggableDispatcherServlet.class);
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			super.doService(request, response);
		} finally {
			logger.debug("############################################");
			logger.debug("Requested uri is {}", request.getRequestURI());
			logger.debug("############################################");
		}
	}
}
