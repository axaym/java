package com.assignment.library.service;

import java.util.List;

import com.assignment.library.entities.Users;

public interface IUsersService {

	public String addUser(Users user);

	public List<Users> getUsers();

}