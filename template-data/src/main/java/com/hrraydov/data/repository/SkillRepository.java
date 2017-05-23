package com.hrraydov.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrraydov.data.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

}
