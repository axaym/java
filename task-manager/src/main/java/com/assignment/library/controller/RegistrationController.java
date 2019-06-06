package com.assignment.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.entities.Users;
import com.assignment.library.service.IUsersService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("registrationController")
//@RequestMapping(value = "/register")
public class RegistrationController {
	
	@Autowired
	private IUsersService userService;
	
	/*@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		Users userDto = new Users();
	    model.addAttribute("user", userDto);
	    return "registration";
	}*/
	@PostMapping("/user/registration")
	public  @ResponseBody String registerUserAccount
		(@RequestBody Users user) {    
	    //User registered = new Users();
	    //if (!result.hasErrors()) {
	         createUserAccount(user);
	    /*}
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }*/
	    // rest of the implementation
	    return "success";
	}
	private String createUserAccount(Users user) {
	    User registered = null;
	    //try {
	        userService.addUser(user);
	    /*} catch (EmailExistsException e) {
	        return null;
	    } */   
	    return "success";
	}
	
}
