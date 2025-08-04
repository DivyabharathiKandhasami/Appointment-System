package com.example.system.jwtUtil;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class jwtUtil {
	
	private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new  Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+86400000))
				.signWith(key )
				.compact();
	}
	
	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}
	public boolean validateToken(String token) {
		try {
		    Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		    return true;
		} catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e) {
		    e.printStackTrace();
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		}
		return false;
	

	}

	
	}


