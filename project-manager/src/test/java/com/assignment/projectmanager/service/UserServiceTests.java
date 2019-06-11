package com.assignment.projectmanager.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.projectmanager.dao.UserJpaRepository;
import com.assignment.projectmanager.entities.ProjectUser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { UserService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class UserServiceTests {

	@Autowired
	private IUserService userService;

	@MockBean
	private UserJpaRepository userJpaRepository;

	@Test
	public void testGetUsers() throws JsonParseException, JsonMappingException, IOException {
		List<ProjectUser> users = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/users.json").getFile());
		users = objectMapper.readValue(file, new TypeReference<List<ProjectUser>>(){});
		
		when(userJpaRepository.findAll()).thenReturn(users);
		List<ProjectUser> usersFromService = userService.getUsers();
		Assertions.assertNotNull(usersFromService);
	}

	@Test
	public void tesAddUser() throws JsonParseException, JsonMappingException, IOException {
		ProjectUser user1 = new ProjectUser();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/user.json").getFile());
		user1 = objectMapper.readValue(file, ProjectUser.class);
				
		when(userJpaRepository.saveAndFlush(user1)).thenReturn(user1);		
		String status = userService.addUser(user1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteUser() throws JsonParseException, JsonMappingException, IOException {
		ProjectUser user1 = new ProjectUser();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/user.json").getFile());
		user1 = objectMapper.readValue(file, ProjectUser.class);
				
		doNothing().when(userJpaRepository).deleteById(user1.getUserId());
		String status = userService.deleteUser(user1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testUpdateUser() throws JsonParseException, JsonMappingException, IOException {
		ProjectUser user1 = new ProjectUser();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/user.json").getFile());
		user1 = objectMapper.readValue(file, ProjectUser.class);
				
		when(userJpaRepository.existsById(user1.getUserId())).thenReturn(true);
		when(userJpaRepository.saveAndFlush(user1)).thenReturn(user1);
		String status = userService.updateUser(user1);
		Assertions.assertEquals("success", status);
	}
}
