package com.hrraydov.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupServiceIT {

	@Autowired
	private GroupService groupService;
	
	@Test
	public void testGetAll(){
		System.err.println(groupService.get());
	}
	
}
