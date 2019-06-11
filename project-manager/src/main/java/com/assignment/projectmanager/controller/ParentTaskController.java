package com.assignment.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.projectmanager.entities.ParentTask;
import com.assignment.projectmanager.service.IParentTaskService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("parentTaskController")
public class ParentTaskController {

	@Autowired
	private IParentTaskService parentTaskService;
		
	@GetMapping("/parenttask/tasks")
    public @ResponseBody List<ParentTask> getTasks() {
        return parentTaskService.getParentTasks();        
    }
	
}
