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

import com.assignment.projectmanager.entities.Task;
import com.assignment.projectmanager.service.ITaskService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("taskController")
public class TaskController {

	@Autowired
	private ITaskService taskService;
		
	@GetMapping("/task/tasks")
    public @ResponseBody List<Task> getTasks() {
        return taskService.getTasks();        
    }
	
	@PostMapping("/task/add")
    public @ResponseBody String addTask(@RequestBody Task task) {
        return taskService.addTask(task);        
    }
	
	@PutMapping("/task/update")
    public @ResponseBody String updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);        
    }
	
	@PostMapping("/task/delete")
    public @ResponseBody String deleteTask(@RequestBody Task task) {
        return taskService.deleteTask(task);        
    }
	
}
