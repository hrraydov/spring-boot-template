package com.hrraydov.security.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.hrraydov.security.User;
import com.hrraydov.security.dto.UserDto;
import com.hrraydov.security.entity.Role;
import com.hrraydov.security.repository.UserRepository;
import com.hrraydov.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		com.hrraydov.security.entity.User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException("user " + username + " not found");
		}
		return buildUserForAuthentication(user);
	}

	@Override
	public UserDto get(String username) {
		User user = loadUserByUsername(username);
		return UserDto.from(user);
	}

	private User buildUserForAuthentication(com.hrraydov.security.entity.User user) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		if (user.getRoles() == null) {
			throw new UsernameNotFoundException("user " + user.getUsername() + " has not autorities");
		}
		for (Role userRole : user.getRoles()) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
		}
		return new User(user.getUsername(), user.getPassword(), true, true, true, true,
				new ArrayList<GrantedAuthority>(setAuths));
	}

	@Override
	public boolean changePassword(final String username, String oldPassword, String newPassword) {
		com.hrraydov.security.entity.User user = userRepository.findOne(username);
		log.info("old password: " + oldPassword);
		log.info("new password: " + newPassword);
		log.info("user password: " + user.getPassword());
		if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			String hashedPassword = passwordEncoder.encode(newPassword);
			log.info("hashed password: " + hashedPassword);
			user.setPassword(hashedPassword);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User createUser(String username, String password, boolean enabled, String... roles) throws Exception {
		Preconditions.checkArgument(!ArrayUtils.isEmpty(roles));
		com.hrraydov.security.entity.User user = userRepository.findOne(username);
		if (user != null) {
			throw new IllegalArgumentException("User " + username + " already exists");
		}
		user = new com.hrraydov.security.entity.User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRoles(Arrays.asList(roles).stream().map(r -> new Role(r)).collect(Collectors.toSet()));
		user = userRepository.save(user);
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public boolean deleteUser(String username) {
		com.hrraydov.security.entity.User user = userRepository.findOne(username);
		if (user == null) {
			return false;
		}
		if (user.getRoles() != null) {
			user.getRoles().clear();
		}
		userRepository.delete(user);
		return true;
	}

}
