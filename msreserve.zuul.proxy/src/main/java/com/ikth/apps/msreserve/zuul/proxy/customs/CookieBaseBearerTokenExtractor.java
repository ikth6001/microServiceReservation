package com.ikth.apps.msreserve.zuul.proxy.customs;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class CookieBaseBearerTokenExtractor extends BearerTokenExtractor
{
	private Logger logger= LoggerFactory.getLogger(CookieBaseBearerTokenExtractor.class);
	private final String BEARER_PREFIX= "Bearer";
	
	protected String extractToken(HttpServletRequest request) {
		// first check the header...
		String token = extractHeaderToken(request);
		if(StringUtils.isEmpty(token)) {
			token= extractCookieToken(request);
		}

		// bearer type allows a request parameter as well
		if (token == null) {
			logger.debug("Token not found in headers. Trying request parameters.");
			token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
			if (token == null) {
				logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
			}
			else {
				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
			}
		}

		return token;
	}

	protected String extractCookieToken(HttpServletRequest request) {
		Cookie[] cookies= request.getCookies();

		if(cookies != null
				&& cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(BEARER_PREFIX.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		
		return null;
	}
}
