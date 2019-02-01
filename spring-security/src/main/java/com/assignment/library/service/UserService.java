package com.assignment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.library.dao.AuthoritiesJpaRepository;
import com.assignment.library.dao.UserJpaRepository;
import com.assignment.library.entities.Authorities;
import com.assignment.library.entities.Users;

@Service("userService")
public class UserService implements IUsersService {
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private AuthoritiesJpaRepository authoritiesJpaRepository;
	

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IUsersService#addUser(com.assignment.library.entities.Users)
	 */
	@Override
	public String addUser(Users user) {
		String pass = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(pass);
		Authorities auth = user.getAuthoritieses().iterator().next();
		userJpaRepository.saveAndFlush(user);
		authoritiesJpaRepository.saveAndFlush(auth);
		return "success";
		
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.library.service.IUsersService#getUsers()
	 */
	@Override
	public List<Users> getUsers() {
		return userJpaRepository.findAll();
		
	}
}
