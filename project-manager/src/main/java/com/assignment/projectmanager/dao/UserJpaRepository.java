package com.assignment.projectmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.projectmanager.entities.ProjectUser;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<ProjectUser, Integer> {

	List<ProjectUser> findByEmployeeId(String employeeId);
	

}
