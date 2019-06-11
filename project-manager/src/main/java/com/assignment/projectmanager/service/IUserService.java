/**
 * 
 */
package com.assignment.projectmanager.service;

import java.util.List;

import com.assignment.projectmanager.entities.ProjectUser;

/**
 * @author Admin
 *
 */
public interface IUserService {

	public List<ProjectUser> getUsers();

	public String addUser(ProjectUser user);

	public String updateUser(ProjectUser user);

	public String deleteUser(ProjectUser user);

}
