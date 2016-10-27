package com.foundation.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.SecretConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class TokenService implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final int TOKEN_EXPIRATION_MINUTES = 15;
	
	@Autowired
	private SecretConfig secretConfig;
	
	public String generateToken(Long id, String login, List<String> roles) {
		
		Date dataAtual = new Date();
		Date expireDate = new DateTime(dataAtual)
							.plusMinutes(TOKEN_EXPIRATION_MINUTES)
							.toDate();
		
		return Jwts.builder()
			.setId(id.toString())
			.setSubject(login)
			.setIssuer(login)
			.setIssuedAt(dataAtual)
			.setExpiration(expireDate)
			.claim("roles", Arrays.asList("GERENTE"))
			.signWith(SignatureAlgorithm.HS256, secretConfig.getSecretKey())
			.compact();
	}
	
	public String refreshToken(Claims claims) {
		Date dataAtual = new Date();
		Date expireDate = new DateTime(dataAtual)
							.plusMinutes(TOKEN_EXPIRATION_MINUTES)
							.toDate();
		
		return Jwts.builder()
			.setId(claims.getId())
			.setSubject(claims.getSubject())
			.setIssuer(claims.getIssuer())
			.setIssuedAt(dataAtual)
			.setExpiration(expireDate)
			.claim("roles", claims.get("roles"))
			.signWith(SignatureAlgorithm.HS256, secretConfig.getSecretKey())
			.compact();
	}
	
	public Claims getClaims(String token) throws ServletException {
		try {
            final Claims claims = Jwts.parser()
            		.setSigningKey(secretConfig.getSecretKey())
            		.parseClaimsJws(token)
            		.getBody();
            return claims;
        } catch (final SignatureException e) {
            throw new ServletException("Invalid token");
        }
	}

}
