package com.hrraydov.security.provider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class SecretKeyProvider {

	public byte[] getAccessTokenKey() throws URISyntaxException, IOException {
		InputStream in = getClass().getResourceAsStream("/jwt-access.key");
		return IOUtils.toByteArray(in);
	}

	public byte[] getRefreshTokenKey() throws URISyntaxException, IOException {
		InputStream in = getClass().getResourceAsStream("/jwt-refresh.key");
		return IOUtils.toByteArray(in);
	}
}
