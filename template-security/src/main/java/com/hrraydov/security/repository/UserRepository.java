package com.hrraydov.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrraydov.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
