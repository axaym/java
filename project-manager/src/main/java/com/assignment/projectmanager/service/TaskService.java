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
		/*if(taskResponse != null && taskResponse.getTaskId() != null) {
			ParentTask parentTask = new ParentTask();
			parentTask.setParentId(taskResponse.getTaskId());
			parentTask.setParentTask(taskResponse.getTask());
			parentTaskJpaRepository.saveAndFlush(parentTask);
		}*/		
		return "success";
	}

	@Override
	public String deleteTask(Task task) {
		taskJpaRepository.deleteById(task.getTaskId());
		//parentTaskJpaRepository.deleteById(task.getTaskId());
		return "success";
	}

	@Override
	public String updateTask(Task task) {
		Integer taskId = task.getTaskId();
		if(taskJpaRepository.existsById(taskId)) {
			taskJpaRepository.saveAndFlush(task);
			/*if(parentTaskJpaRepository.existsById(taskId)) {
				ParentTask parentTask = new ParentTask();
				parentTask.setParentId(taskId);
				parentTask.setParentTask(task.getTask());
				parentTaskJpaRepository.saveAndFlush(parentTask);
				return "Task updated successfully";
			}*/
			return "success";
		}		
		
		return "fail";
	}

}
