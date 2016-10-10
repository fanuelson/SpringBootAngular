package com.foundation.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String generateToken(Long id, String login, List<String> roles) {
		
		Date dataAtual = new Date();
		Date expireDate = new DateTime(dataAtual).plusMinutes(15).toDate();
		
		return Jwts.builder()
			.setId(id.toString())
			.setSubject(login)
			.setIssuer(login)
			.setIssuedAt(dataAtual)
			.setExpiration(expireDate)
			.claim("roles", Arrays.asList("GERENTE"))
			.signWith(SignatureAlgorithm.HS256, "secretkey")
			.compact();
	}
	
	public String refreshToken(Claims claims) {
		Date dataAtual = new Date();
		Date expireDate = new DateTime(dataAtual).plusMinutes(15).toDate();
		
		return Jwts.builder()
			.setId(claims.getId())
			.setSubject(claims.getSubject())
			.setIssuer(claims.getIssuer())
			.setIssuedAt(dataAtual)
			.setExpiration(expireDate)
			.claim("roles", claims.get("roles"))
			.signWith(SignatureAlgorithm.HS256, "secretkey")
			.compact();
	}
	
	public Claims getClaims(String token) throws ServletException {
		try {
            final Claims claims = Jwts.parser()
            		.setSigningKey("secretkey")
            		.parseClaimsJws(token)
            		.getBody();
            return claims;
        } catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }
	}

}
