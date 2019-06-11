package com.assignment.projectmanager.dao;



import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.projectmanager.entities.Task;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskDAOTest {

	@Autowired
	private TaskJpaRepository taskJpaRepository;

	@Test
	public void testSaveAndFindTask() throws Exception {
		Task task1 = getTaskObj("Task 23213123", 2, new Date(), new Date(), 104);
		taskJpaRepository.saveAndFlush(task1);

		List<Task> task = taskJpaRepository.findByTask(task1.getTask());

		Assertions.assertEquals(task1.getTask(), task.get(0).getTask());
		Assertions.assertEquals(task1.getPriority(), task.get(0).getPriority());
		
	}
	
	@Test
	public void testDeleteAndFindTask() throws Exception {
		Task task1 = getTaskObj("Task 2 for deletion test", 3, new Date(), new Date(), 33);
		taskJpaRepository.saveAndFlush(task1);
		List<Task> task = taskJpaRepository.findByTask(task1.getTask());
		taskJpaRepository.deleteById(task.get(0).getTaskId());

		List<Task> taskAfterDeletion = taskJpaRepository.findByTask(task.get(0).getTask());
		Assertions.assertEquals(0, taskAfterDeletion.size());
	}

	private Task getTaskObj(String task, int priority, Date startDate, Date endDate, int taskId) {
		Task taskObj = new Task();
		taskObj.setTask(task);
		taskObj.setTaskId(taskId);
		taskObj.setPriority(priority);
		taskObj.setStartDate(startDate);
		taskObj.setEndDate(endDate);
		return taskObj;
	}
}
