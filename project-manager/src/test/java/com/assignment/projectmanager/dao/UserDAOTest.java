package com.assignment.projectmanager.dao;



import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.projectmanager.entities.ProjectUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDAOTest {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Test
	public void testSaveAndFindUser() throws Exception {
		
		ProjectUser user1 = new ProjectUser();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/user.json").getFile());
		user1 = objectMapper.readValue(file, ProjectUser.class);
		
		userJpaRepository.saveAndFlush(user1);

		List<ProjectUser> user = userJpaRepository.findByEmployeeId(user1.getEmployeeId());

		Assertions.assertEquals(user1.getEmployeeId(), user.get(0).getEmployeeId());
		Assertions.assertEquals(user1.getFirstName(), user.get(0).getFirstName());
		
	}
	
	@Test
	public void testDeleteAndFindUser() throws Exception {
		ProjectUser user1 = new ProjectUser();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/user2.json").getFile());
		user1 = objectMapper.readValue(file, ProjectUser.class);
		
		userJpaRepository.saveAndFlush(user1);
		List<ProjectUser> user = userJpaRepository.findByEmployeeId(user1.getEmployeeId());
		userJpaRepository.deleteById(user.get(0).getUserId());

		List<ProjectUser> userAfterDeletion = userJpaRepository.findByEmployeeId(user.get(0).getEmployeeId());
		Assertions.assertEquals(0, userAfterDeletion.size());
	}
	
}
