package com.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.taskmanager.dao.ParentTaskJpaRepository;
import com.assignment.taskmanager.dao.TaskJpaRepository;
import com.assignment.taskmanager.entities.ParentTask;
import com.assignment.taskmanager.entities.Task;

@Service("taskService")
public class TaskService implements ITaskService {

	@Autowired
	private TaskJpaRepository taskJpaRepository;
	
	@Autowired
	private ParentTaskJpaRepository parentTaskJpaRepository;

	@Override
	public List<Task> getTasks() {
		return taskJpaRepository.findAll();
	}

	@Override
	public String addTask(Task task) {
		Task taskResponse = taskJpaRepository.saveAndFlush(task);
		if(taskResponse != null && taskResponse.getTaskId() != null) {
			ParentTask parentTask = new ParentTask();
			parentTask.setParentId(taskResponse.getTaskId());
			parentTask.setParentTask(taskResponse.getTask());
			parentTaskJpaRepository.saveAndFlush(parentTask);
		}		
		return "success";
	}

	@Override
	public String deleteTask(Task task) {
		taskJpaRepository.deleteById(task.getTaskId());
		parentTaskJpaRepository.deleteById(task.getTaskId());
		return "success";
	}

	@Override
	public String updateTask(Task task) {
		Integer taskId = task.getTaskId();
		if(taskJpaRepository.existsById(taskId)) {
			taskJpaRepository.saveAndFlush(task);
			if(parentTaskJpaRepository.existsById(taskId)) {
				ParentTask parentTask = new ParentTask();
				parentTask.setParentId(taskId);
				parentTask.setParentTask(task.getTask());
				parentTaskJpaRepository.saveAndFlush(parentTask);
				return "Task updated successfully";
			}
			return "Task updated successfully";
		}		
		
		return "task does not exist to update";
	}

}
