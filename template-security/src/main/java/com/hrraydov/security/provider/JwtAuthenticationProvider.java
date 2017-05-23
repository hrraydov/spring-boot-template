package com.hrraydov.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.hrraydov.security.dto.JwtAuthToken;
import com.hrraydov.security.exception.JwtAuthenticationException;
import com.hrraydov.security.service.JwtService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtService jwtService;

	@Autowired
	public JwtAuthenticationProvider(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			return jwtService.verifyAccessToken((String) authentication.getCredentials());
		} catch (Exception e) {
			throw new JwtAuthenticationException("Failed to verify token", e);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthToken.class.equals(authentication);
	}

}
