package com.assignment.projectmanager.service;

import java.util.List;

import com.assignment.projectmanager.entities.Task;

public interface ITaskService {

	/**
	 * @return
	 */
	public List<Task> getTasks();
	
	/**
	 * create a task and a parent task
	 * @param task
	 * @return
	 */
	public String addTask(Task task);
	
	/**
	 * delete task and parent task entry
	 * @param task
	 * @return
	 */
	public String deleteTask(Task task);
	
	/**
	 * update task and corresponding parent task name entry
	 * @param task
	 * @return
	 */
	public String updateTask(Task task);

	

}