package com.assignment.projectmanager.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.projectmanager.dao.ProjectJpaRepository;
import com.assignment.projectmanager.entities.Project;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { ProjectService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class ProjectServiceTests {

	@Autowired
	private IProjectService projectService;

	@MockBean
	private ProjectJpaRepository projectJpaRepository;

	@Test
	public void testGetProjects() throws JsonParseException, JsonMappingException, IOException {
		List<Project> projects = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/projects.json").getFile());
		projects = objectMapper.readValue(file, new TypeReference<List<Project>>(){});
		
		when(projectJpaRepository.findAll()).thenReturn(projects);
		List<Project> projectsFromService = projectService.getProjects();
		Assertions.assertNotNull(projectsFromService);
	}

	@Test
	public void tesAddProject() throws JsonParseException, JsonMappingException, IOException {
		Project project1 = new Project();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/project.json").getFile());
		project1 = objectMapper.readValue(file, Project.class);
				
		when(projectJpaRepository.saveAndFlush(project1)).thenReturn(project1);		
		String status = projectService.addProject(project1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteProject() throws JsonParseException, JsonMappingException, IOException {
		Project project1 = new Project();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/project.json").getFile());
		project1 = objectMapper.readValue(file, Project.class);
				
		doNothing().when(projectJpaRepository).deleteById(project1.getProjectId());
		String status = projectService.deleteProject(project1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testUpdateProject() throws JsonParseException, JsonMappingException, IOException {
		Project project1 = new Project();
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data/project.json").getFile());
		project1 = objectMapper.readValue(file, Project.class);
				
		when(projectJpaRepository.existsById(project1.getProjectId())).thenReturn(true);
		when(projectJpaRepository.saveAndFlush(project1)).thenReturn(project1);
		String status = projectService.updateProject(project1);
		Assertions.assertEquals("success", status);
	}
}
