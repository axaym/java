package com.assignment.libraryJdbc.dao;

import java.util.List;

import com.assignment.libraryJdbc.entities.Subject;

public interface ISubjectDAO {

	/**
	 * search subject by subject title keyword
	 * 
	 * @param subjectName
	 * @return
	 */
	public List<Subject> searchSubject(String subjectName);

	/**
	 * add a new subject
	 * 
	 * @param subject
	 * @return
	 */
	public boolean addSubject(Subject subject);

	/**
	 * delete subject by subject_id. related books will be automatically deleted
	 * due to cascade foreign key referential integrity
	 * 
	 * @param subjectId
	 * @return
	 */
	public boolean deleteSubject(long subjectId);

}
