package com.assignment.projectmanager.dao;



import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.projectmanager.entities.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskDAOTest {

	@Autowired
	private TaskJpaRepository taskJpaRepository;

	//@Test
	public void testSaveAndFindTask() throws Exception {
		
		Task task1 = new Task();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/task.json").getFile());
		task1 = objectMapper.readValue(file, Task.class);
		
		taskJpaRepository.saveAndFlush(task1);

		List<Task> task = taskJpaRepository.findByTask(task1.getTask());

		Assertions.assertEquals(task1.getTask(), task.get(0).getTask());
		Assertions.assertEquals(task1.getPriority(), task.get(0).getPriority());
		
	}
	
	//@Test
	public void testDeleteAndFindTask() throws Exception {
		Task task1 = new Task();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/task2.json").getFile());
		task1 = objectMapper.readValue(file, Task.class);
		
		taskJpaRepository.saveAndFlush(task1);
		List<Task> task = taskJpaRepository.findByTask(task1.getTask());
		taskJpaRepository.deleteById(task.get(0).getTaskId());

		List<Task> taskAfterDeletion = taskJpaRepository.findByTask(task.get(0).getTask());
		Assertions.assertEquals(0, taskAfterDeletion.size());
	}
	
}
