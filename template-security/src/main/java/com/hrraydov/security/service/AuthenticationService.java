package com.hrraydov.security.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

	Authentication get(String username);
}
