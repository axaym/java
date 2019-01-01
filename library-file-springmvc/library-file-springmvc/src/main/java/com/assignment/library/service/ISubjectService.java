package com.assignment.library.service;

import java.util.List;

import com.assignment.library.entities.Subject;

public interface ISubjectService {

	/**
	 * @param subject
	 * @return
	 */
	public List<Subject> searchSubjects(Subject subject);

	/**
	 * @param subject
	 * @return
	 */
	public String addSubject(Subject subject);

	/**
	 * @return
	 */
	public List<Subject> getAllSubjects();

	/**
	 * @param subject
	 * @return
	 */
	public String deleteSubject(Subject subject);

}