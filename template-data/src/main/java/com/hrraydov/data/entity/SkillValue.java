package com.hrraydov.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hrraydov.data.entity.key.SkillValueId;
import com.hrraydov.security.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill_value")
@IdClass(value = SkillValueId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillValue {

	@Id
	@ManyToOne
	@JoinColumn(name = "skill", referencedColumnName = "name")
	private Skill skill;

	@Id
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "username")
	private User user;

	private Integer value;

}
