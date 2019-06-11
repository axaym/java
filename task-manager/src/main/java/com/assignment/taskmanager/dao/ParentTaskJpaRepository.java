/**
 * 
 */
package com.assignment.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.taskmanager.entities.ParentTask;

/**
 * @author Admin
 *
 */
@Repository("parentTaskJpaRepository")
public interface ParentTaskJpaRepository extends JpaRepository<ParentTask, Integer> {
	
		
}