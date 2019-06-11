/**
 * 
 */
package com.assignment.projectmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.projectmanager.entities.Task;

/**
 * @author Admin
 *
 */
@Repository("taskJpaRepository")
public interface TaskJpaRepository extends JpaRepository<Task, Integer> {
	
	public List<Task> findByTaskId(Integer taskId);

	public List<Task> findByTask(String task);
		
}
