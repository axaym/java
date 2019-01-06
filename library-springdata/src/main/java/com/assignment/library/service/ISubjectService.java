package com.assignment.library.service;

import java.util.List;

import com.assignment.library.entities.Subject;

public interface ISubjectService {

	/**
	 * @param subject
	 * @return
	 */
	public List<Subject> searchSubject(Subject subject);
	
	/**
	 * @param subject
	 * @return
	 */
	public List<Subject> searchSubjectByDurationEqual(Subject subject);

	/**
	 * @param subject
	 * @return
	 */
	public String addSubject(Subject subject);

	/**
	 * delete subject by subject_id. related books will be automatically deleted due
	 * to foreign key cascade referential integrity
	 * 
	 * @param subject
	 * @return
	 */
	public String deleteSubject(Subject subject);

}