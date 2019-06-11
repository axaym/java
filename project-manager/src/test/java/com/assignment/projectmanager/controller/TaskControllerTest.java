package com.assignment.projectmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.projectmanager.entities.Task;
import com.assignment.projectmanager.service.ITaskService;

@ContextConfiguration(classes = { TaskController.class })
@WebMvcTest
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ITaskService taskService;

	@Test
	public void testGetTasks() throws Exception {
		Task task1 = getTaskObj("Task 1", 2, new Date(), new Date(), 1);
		Task task2 = getTaskObj("Task 2", 2, new Date(), new Date(), 2);
		

		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		when(taskService.getTasks()).thenReturn(tasks);
		mockMvc.perform(MockMvcRequestBuilders.get("/task/tasks").with(user("axay"))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
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