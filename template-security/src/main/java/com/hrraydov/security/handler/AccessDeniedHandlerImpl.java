package com.hrraydov.security.handler;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		String message;
		if (accessDeniedException.getCause() != null) {
			message = accessDeniedException.getCause().getMessage();
		} else {
			message = accessDeniedException.getMessage();
		}
		byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
		response.getOutputStream().write(body);
	}

}
