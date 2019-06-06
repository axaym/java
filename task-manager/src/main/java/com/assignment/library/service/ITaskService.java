package com.assignment.library.service;

import java.util.List;

import com.assignment.library.entities.Task;

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