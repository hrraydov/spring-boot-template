package com.hrraydov.data.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

	@Id
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category", referencedColumnName = "name")
	private Category category;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<SkillValue> skillValues;

	@Override
	public String toString() {
		return "Skill [name=" + name + ", category=" + category.getName() + "]";
	}
}
