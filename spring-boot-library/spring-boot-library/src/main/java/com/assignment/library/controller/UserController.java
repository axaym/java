package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.entities.Users;
import com.assignment.library.service.IUsersService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("userController")
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private IUsersService usersService;
	
	@GetMapping("/users")
    public @ResponseBody List<Users> getAllUsers() {
        return usersService.getUsers();        
    }
	
	@PostMapping("/add")
    public @ResponseBody String addBook(@RequestBody Users user) {
        return usersService.addUser(user);
    }
}
