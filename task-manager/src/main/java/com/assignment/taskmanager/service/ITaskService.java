package com.assignment.taskmanager.service;

import java.util.List;

import com.assignment.taskmanager.entities.Task;

public interface ITaskService {

	/**
	 * @return
	 */
	public List<Task> getTasks();
	
	/**
	 * @param task
	 * @return
	 */
	public String addTask(Task task);
	
	/**
	 * @param task
	 * @return
	 */
	public String deleteTask(Task task);
	
	/**
	 * @param task
	 * @return
	 */
	public String updateTask(Task task);

	

}