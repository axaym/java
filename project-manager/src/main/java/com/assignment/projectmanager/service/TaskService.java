package com.assignment.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.projectmanager.dao.TaskJpaRepository;
import com.assignment.projectmanager.entities.Task;

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
			return "success";
		}		
		
		return "fail";
	}

	@Override
	public List<Task> getTasksByProject(Task task) {
		return taskJpaRepository.findAllByProjectId(task.getProjectId());
	}

}
