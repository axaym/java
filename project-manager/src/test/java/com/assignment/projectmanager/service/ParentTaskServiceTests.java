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

import com.assignment.projectmanager.dao.ParentTaskJpaRepository;
import com.assignment.projectmanager.entities.ParentTask;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { ParentTaskService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class ParentTaskServiceTests {

	@Autowired
	private IParentTaskService parentTaskService;

	@MockBean
	private ParentTaskJpaRepository parentTaskJpaRepository;

	@Test
	public void testGetParentTasks() throws JsonParseException, JsonMappingException, IOException {
		List<ParentTask> tasks = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/parenttasks.json").getFile());
		tasks = objectMapper.readValue(file, new TypeReference<List<ParentTask>>(){});
		
		when(parentTaskJpaRepository.findAll()).thenReturn(tasks);
		List<ParentTask> parenTtasksFromService = parentTaskService.getParentTasks();
		Assertions.assertNotNull(parenTtasksFromService);
	}

	@Test
	public void tesAddTask() throws JsonParseException, JsonMappingException, IOException {
		ParentTask task1 = new ParentTask();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/parenttask.json").getFile());
		task1 = objectMapper.readValue(file, ParentTask.class);
				
		when(parentTaskJpaRepository.saveAndFlush(task1)).thenReturn(task1);		
		String status = parentTaskService.addParentTask(task1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteParentTask() throws JsonParseException, JsonMappingException, IOException {
		ParentTask task1 = new ParentTask();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/parenttask.json").getFile());
		task1 = objectMapper.readValue(file, ParentTask.class);
				
		doNothing().when(parentTaskJpaRepository).deleteById(task1.getParentId());
		String status = parentTaskService.deleteParentTask(task1);
		Assertions.assertEquals("success", status);
	}

}
