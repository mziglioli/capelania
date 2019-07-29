package com.capelania.config;

import com.capelania.model.User;
import com.capelania.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenHandler {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private UserService userService;

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).parseClaimsJws(token).getBody()
				.getSubject();
		return userService.findByUsername(username);
	}

	public String createTokenForUser(String username) {
		return Jwts.builder().setSubject(username)
			.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512).compact();
	}
}
