package com.hrraydov.data.entity.key;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillValueId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String skill;

	private String user;

}
