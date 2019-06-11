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

import com.assignment.projectmanager.entities.ProjectUser;
import com.assignment.projectmanager.service.IUserService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("userController")
public class UserController {

	@Autowired
	private IUserService userService;
		
	@GetMapping("/user/users")
    public @ResponseBody List<ProjectUser> getUsers() {
        return userService.getUsers();        
    }
	
	@PostMapping("/user/add")
    public @ResponseBody String addUser(@RequestBody ProjectUser user) {
        return userService.addUser(user);        
    }
	
	@PutMapping("/user/update")
    public @ResponseBody String updateUser(@RequestBody ProjectUser user) {
        return userService.updateUser(user);        
    }
	
	@PostMapping("/user/delete")
    public @ResponseBody String deleteUser(@RequestBody ProjectUser user) {
        return userService.deleteUser(user);        
    }
	
}
