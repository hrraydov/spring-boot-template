package com.hrraydov.security;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JwtsBuilder {

	private static final String ISSUER = "com.hrraydov";

	private JwtsBuilder() {
	}

	public static String tokenFor(byte[] secretKey, Date expiration, Authentication auth)
			throws IOException, URISyntaxException {
		return Jwts.builder().setSubject(auth.getPrincipal().toString()).setExpiration(expiration).setIssuer(ISSUER)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
}
