/**
 * 
 */
package com.assignment.projectmanager.service;

import java.util.List;

import com.assignment.projectmanager.entities.Project;

/**
 * @author Admin
 *
 */
public interface IProjectService {

	public List<Project> getProjects();

	public String addProject(Project project);

	public String updateProject(Project project);

	public String deleteProject(Project project);

}
