package com.hrraydov.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrraydov.data.entity.Category;
import com.hrraydov.data.repository.GroupRepository;
import com.hrraydov.service.GroupService;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Category> get() {
		return groupRepository.findAll();
	}

}
