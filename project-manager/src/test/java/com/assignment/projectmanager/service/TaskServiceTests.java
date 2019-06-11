package com.assignment.projectmanager.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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

@ContextConfiguration(classes = { TaskService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class TaskServiceTests {

	@Autowired
	private ITaskService taskService;

	@MockBean
	private TaskJpaRepository taskJpaRepository;

	@Test
	public void testGetTasks() {
		Task task1 = getTaskObj("Task 1", 2, new Date(), new Date(), 1);
		Task task2 = getTaskObj("Task 2", 2, new Date(), new Date(), 2);

		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		when(taskJpaRepository.findAll()).thenReturn(tasks);
		List<Task> tasksFromService = taskService.getTasks();
		Assertions.assertNotNull(tasksFromService);
	}

	@Test
	public void tesAddTask() {
		Task task1 = getTaskObj("Task 1", 2, new Date(), new Date(), 1);
				
		when(taskJpaRepository.saveAndFlush(task1)).thenReturn(task1);		
		String status = taskService.addTask(task1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteTask() {
		Task task1 = getTaskObj("Task 1", 2, new Date(), new Date(), 1);
				
		doNothing().when(taskJpaRepository).deleteById(task1.getTaskId());
		String status = taskService.deleteTask(task1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testUpdateTask() {
		Task task1 = getTaskObj("Task 1", 2, new Date(), new Date(), 1);
				
		when(taskJpaRepository.existsById(task1.getTaskId())).thenReturn(true);
		when(taskJpaRepository.saveAndFlush(task1)).thenReturn(task1);
		String status = taskService.updateTask(task1);
		Assertions.assertEquals("success", status);
	}

	private Task getTaskObj(String task, int priority, Date startDate, Date endDate, int taskId) {
		Task taskObj = new Task();
		taskObj.setTask(task);
		taskObj.setTaskId(taskId);
		taskObj.setPriority(priority);
		taskObj.setStartDate(startDate);
		taskObj.setStartDate(endDate);
		return taskObj;
	}
}
