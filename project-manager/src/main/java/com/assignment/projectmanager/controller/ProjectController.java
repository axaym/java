package com.assignment.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.projectmanager.entities.Project;
import com.assignment.projectmanager.service.IProjectService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("projectController")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
		
	@GetMapping("/project/projects")
    public @ResponseBody List<Project> getProjects() {
        return projectService.getProjects();        
    }
	
	@PostMapping("/project/add")
    public @ResponseBody String addProject(@RequestBody Project project) {
        return projectService.addProject(project);        
    }
	
	@PutMapping("/project/update")
    public @ResponseBody String updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);        
    }
	
	@PostMapping("/project/delete")
    public @ResponseBody String deleteProject(@RequestBody Project project) {
        return projectService.deleteProject(project);        
    }
	
}
