package com.assignment.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.projectmanager.dao.UserJpaRepository;
import com.assignment.projectmanager.entities.ProjectUser;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	public List<ProjectUser> getUsers() {
		return userJpaRepository.findAll();
	}

	@Override
	public String addUser(ProjectUser user) {
		userJpaRepository.saveAndFlush(user);
		return "success";
	}

	@Override
	public String deleteUser(ProjectUser user) {
		userJpaRepository.deleteById(user.getUserId());
		return "success";
	}

	@Override
	public String updateUser(ProjectUser user) {
		Integer userId = user.getUserId();
		if(userJpaRepository.existsById(userId)) {
			userJpaRepository.saveAndFlush(user);
			return "success";
		}	
		return "fail";
	}

}
