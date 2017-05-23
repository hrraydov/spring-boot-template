package com.hrraydov.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hrraydov.security.User;
import com.hrraydov.security.dto.UserDto;

public interface UserService extends UserDetailsService {

	@Override
	User loadUserByUsername(String username) throws UsernameNotFoundException;

	UserDto get(String username);

	boolean changePassword(final String username, String oldPassword, String newPassword);

	User createUser(String username, String password, boolean enabled, String... roles) throws Exception;

	boolean deleteUser(String username);
}
