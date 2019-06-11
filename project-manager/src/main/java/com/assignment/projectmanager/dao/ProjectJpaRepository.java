package com.assignment.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.projectmanager.entities.Project;

@Repository("projectJpaRepository")
public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {
	

}
