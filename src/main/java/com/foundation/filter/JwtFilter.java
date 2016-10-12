package com.foundation.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.foundation.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		
		if(isLoginRequest(request)){
			chain.doFilter(req, res);
			return;
		}
		
		final String token = getRequestToken(request);

		try {
			final Claims claims = getTokenService().getClaims(token);
			final DateTime expirationDate = new DateTime(claims.getExpiration());
			final Period periodo = new Period(new DateTime(new Date()), new DateTime(expirationDate));
			final int standardMinutesRemaining = Math.abs(periodo.toStandardMinutes().getMinutes());
			if(standardMinutesRemaining >= 0 && standardMinutesRemaining < 5 ) {
				response.addHeader("refresh-token", getTokenService().refreshToken(claims));
			}
			response.addHeader("minutes-remaining", new Integer(standardMinutesRemaining).toString());
		} catch (final SignatureException e) {
			throw new ServletException("Invalid token");
		} catch (final ExpiredJwtException e) {
			throw new ServletException("Invalid token");
		}

		chain.doFilter(req, res);
	}
	
	private boolean isLoginRequest(HttpServletRequest request) {
		return request.getServletPath().equals("/api/auth/login");
	}

	private String getRequestToken(final HttpServletRequest request) throws ServletException {
		final String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ServletException("Missing or invalid Authorization header.");
		}
		return authHeader.substring(7);
	}
	
	private TokenService getTokenService() {
		return WebApplicationContextUtils.
				getRequiredWebApplicationContext(getServletContext()).
				getBean(TokenService.class);
	}

}
