/**
 * 
 */
package com.assignment.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.taskmanager.entities.Task;

/**
 * @author Admin
 *
 */
@Repository("taskJpaRepository")
public interface TaskJpaRepository extends JpaRepository<Task, Integer> {
	
		
}
