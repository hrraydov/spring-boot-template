package com.hrraydov.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class RoleDto implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;

	public RoleDto() {
	}

	public RoleDto(String role) {
		Assert.hasText(role, "A granted authority textual representation is required");
		this.authority = role;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String role) {
		this.authority = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof RoleDto) {
			return authority.equals(((RoleDto) obj).authority);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.authority.hashCode();
	}

	@Override
	public String toString() {
		return this.authority;
	}

}
