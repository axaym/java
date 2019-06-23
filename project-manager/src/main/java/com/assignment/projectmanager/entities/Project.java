package com.assignment.projectmanager.entities;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Project implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7259309672380978191L;

	private Integer projectId;
	
	private Integer userId;
	
	private String project;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer priority;

	@JsonIgnore
	private Set<Task> tasks;
	
	private Integer taskCount;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private ProjectUser user;
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public ProjectUser getUser() {
		return user;
	}

	public void setUser(ProjectUser user) {
		this.user = user;
	}

	public Integer getTaskCount() {
		return tasks.size();
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}	
		
	
}