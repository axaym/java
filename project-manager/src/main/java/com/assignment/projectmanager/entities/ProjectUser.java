package com.assignment.projectmanager.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 142275689616941360L;

	private Integer userId;
	
	private String employeeId;
	
	private String firstName;
	
	private String lastName;
		
	@JsonIgnore
	private Set<Task> tasks;
	
	@JsonIgnore
	private Set<Project> projects;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}	
	
	
}