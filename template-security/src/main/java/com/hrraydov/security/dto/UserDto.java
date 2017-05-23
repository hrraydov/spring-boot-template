package com.hrraydov.security.dto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hrraydov.security.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private boolean enabled;
	private boolean authenticated;
	private Set<RoleDto> authorities;

	public final static UserDto from(User user) {
		Set<RoleDto> authorities = user.getAuthorities().stream().map(a -> new RoleDto(a.getAuthority()))
				.collect(Collectors.toSet());
		return UserDto.builder().username(user.getUsername()).enabled(user.isEnabled()).authorities(authorities)
				.build();
	}

	public final static UserDto from(com.hrraydov.security.entity.User user) {
		Set<RoleDto> authorities = user.getRoles().stream().map(r -> new RoleDto(r.getName()))
				.collect(Collectors.toSet());
		return UserDto.builder().username(user.getUsername()).enabled(true).authorities(authorities)
				.build();
	}

	@Override
	public String getName() {
		return username;
	}

	@Override
	public Collection<RoleDto> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
