/**
 * 
 */
package com.assignment.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Subject;

/**
 * @author Admin
 *
 */

@Repository
public interface SubjectJpaRepository extends JpaRepository<Subject, Integer> {

	/**
	 * @param subtitle
	 * @return
	 */
	public List<Subject> findBySubtitleLike(String subtitle);
}
