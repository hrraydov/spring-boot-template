package com.hrraydov.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrraydov.data.entity.Category;

@Repository
public interface GroupRepository extends JpaRepository<Category, String>{

}
