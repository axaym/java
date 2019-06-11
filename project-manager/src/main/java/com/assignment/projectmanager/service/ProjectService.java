package com.assignment.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.projectmanager.dao.ProjectJpaRepository;
import com.assignment.projectmanager.entities.Project;

@Service("projectService")
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectJpaRepository projectJpaRepository;

	@Override
	public List<Project> getProjects() {
		return projectJpaRepository.findAll();
	}

	@Override
	public String addProject(Project project) {
		projectJpaRepository.saveAndFlush(project);
		return "success";
	}

	@Override
	public String deleteProject(Project project) {
		projectJpaRepository.deleteById(project.getProjectId());
		return "success";
	}

	@Override
	public String updateProject(Project project) {
		Integer projectId = project.getProjectId();
		if(projectJpaRepository.existsById(projectId)) {
			projectJpaRepository.saveAndFlush(project);
			return "success";
		}	
		return "fail";
	}

}
