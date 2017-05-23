package com.hrraydov.security.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hrraydov.security.JwtsBuilder;
import com.hrraydov.security.provider.SecretKeyProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JwtService {

	private final SecretKeyProvider secretKeyProvider;
	private final AuthenticationService authService;
	@Value("${jwt.access-token.expiration}")
	private Integer accessTokenExp;
	@Value("${jwt.refresh-token.expiration}")
	private Integer refreshTokenExp;

	@Autowired
	public JwtService(SecretKeyProvider secretKeyProvider, AuthenticationService authService) {
		this.secretKeyProvider = secretKeyProvider;
		this.authService = authService;
	}

	public String accessTokenFor(Authentication auth) throws URISyntaxException, IOException {
		byte[] secretKey = secretKeyProvider.getAccessTokenKey();
		Date expiration = DateTime.now().plusSeconds(accessTokenExp).toDate();
		return JwtsBuilder.tokenFor(secretKey, expiration, auth);
	}

	public String refreshTokenFor(Authentication auth) throws URISyntaxException, IOException {
		byte[] secretKey = secretKeyProvider.getRefreshTokenKey();
		Date expiration = DateTime.now().plusSeconds(refreshTokenExp).toDate();
		return JwtsBuilder.tokenFor(secretKey, expiration, auth);
	}

	public Authentication verifyAccessToken(String token) throws URISyntaxException, IOException {
		byte[] secretKey = secretKeyProvider.getAccessTokenKey();
		return verify(token, secretKey);
	}

	public Authentication verifyRefreshToken(String token) throws URISyntaxException, IOException {
		byte[] secretKey = secretKeyProvider.getRefreshTokenKey();
		return verify(token, secretKey);
	}

	private Authentication verify(String token, byte[] secretKey) throws URISyntaxException, IOException {
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		String username = claims.getBody().getSubject();
		Authentication auth = authService.get(username);
		auth.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return auth;
	}

	public String prolong(String token) throws URISyntaxException, IOException {
		byte[] secretKey = secretKeyProvider.getAccessTokenKey();
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		String username = claims.getBody().getSubject();
		Authentication auth = authService.get(username);
		Date exp = DateTime.now().plusSeconds(accessTokenExp).toDate();
		return JwtsBuilder.tokenFor(secretKey, exp, auth);

	}

}
