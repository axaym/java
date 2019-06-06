package com.assignment.taskmanager.service;

import java.util.List;

import com.assignment.taskmanager.entities.ParentTask;

public interface IParentTaskService {

	/**
	 * @return
	 */
	public List<ParentTask> getParentTasks();
	
	/**
	 * @param parentTask
	 * @return
	 */
	public String addParentTask(ParentTask parentTask);
	
	/**
	 * @param parentTask
	 * @return
	 */
	public String deleteParentTask(ParentTask parentTask);
	
	/**
	 * @param parentTask
	 * @return
	 */
	public String updateParentTask(ParentTask parentTask);	

}