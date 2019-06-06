/**
 * 
 */
package com.assignment.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Task;

/**
 * @author Admin
 *
 */
@Repository("taskJpaRepository")
public interface TaskJpaRepository extends JpaRepository<Task, Integer> {
	
		
}
