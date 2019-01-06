/**
 * 
 */
package com.assignment.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Book;

/**
 * @author Admin
 *
 */
@Repository
public interface BookJpaRepository extends JpaRepository<Book, Integer> {
	
	/**
	 * @param title
	 * @return
	 */
	public List<Book> findByTitleLike(String title);
	
	public List<Book> findByTitle(String title);
	
}
