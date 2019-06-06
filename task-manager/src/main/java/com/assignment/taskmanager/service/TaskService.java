package com.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.taskmanager.dao.TaskJpaRepository;
import com.assignment.taskmanager.entities.Task;

@Service("taskService")
public class TaskService implements ITaskService {

	@Autowired
	private TaskJpaRepository taskJpaRepository;

	@Override
	public List<Task> getTasks() {
		return taskJpaRepository.findAll();
	}

	@Override
	public String addTask(Task task) {
		taskJpaRepository.saveAndFlush(task);
		return "success";
	}

	@Override
	public String deleteTask(Task task) {
		taskJpaRepository.deleteById(task.getTaskId());
		return "success";
	}

	@Override
	public String updateTask(Task task) {
		Integer taskId = task.getTaskId();
		if(taskJpaRepository.existsById(taskId)) {
			taskJpaRepository.saveAndFlush(task);
			return "Task updated successfully";
		}
		return "task does not exist to update";
	}

}
