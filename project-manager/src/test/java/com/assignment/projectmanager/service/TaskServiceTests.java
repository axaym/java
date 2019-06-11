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

import com.assignment.projectmanager.dao.TaskJpaRepository;
import com.assignment.projectmanager.entities.Task;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { TaskService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class TaskServiceTests {

	@Autowired
	private ITaskService taskService;

	@MockBean
	private TaskJpaRepository taskJpaRepository;

	@Test
	public void testGetTasks() throws JsonParseException, JsonMappingException, IOException {
		List<Task> tasks = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/tasks.json").getFile());
		tasks = objectMapper.readValue(file, new TypeReference<List<Task>>(){});
		
		when(taskJpaRepository.findAll()).thenReturn(tasks);
		List<Task> tasksFromService = taskService.getTasks();
		Assertions.assertNotNull(tasksFromService);
	}

	@Test
	public void tesAddTask() throws JsonParseException, JsonMappingException, IOException {
		Task task1 = new Task();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/task.json").getFile());
		task1 = objectMapper.readValue(file, Task.class);
				
		when(taskJpaRepository.saveAndFlush(task1)).thenReturn(task1);		
		String status = taskService.addTask(task1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteTask() throws JsonParseException, JsonMappingException, IOException {
		Task task1 = new Task();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/task.json").getFile());
		task1 = objectMapper.readValue(file, Task.class);
				
		doNothing().when(taskJpaRepository).deleteById(task1.getTaskId());
		String status = taskService.deleteTask(task1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testUpdateTask() throws JsonParseException, JsonMappingException, IOException {
		Task task1 = new Task();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/task.json").getFile());
		task1 = objectMapper.readValue(file, Task.class);
				
		when(taskJpaRepository.existsById(task1.getTaskId())).thenReturn(true);
		when(taskJpaRepository.saveAndFlush(task1)).thenReturn(task1);
		String status = taskService.updateTask(task1);
		Assertions.assertEquals("success", status);
	}
}
