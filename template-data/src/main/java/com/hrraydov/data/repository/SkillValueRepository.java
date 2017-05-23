package com.hrraydov.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrraydov.data.entity.SkillValue;
import com.hrraydov.data.entity.key.SkillValueId;

@Repository
public interface SkillValueRepository extends JpaRepository<SkillValue, SkillValueId> {

}
