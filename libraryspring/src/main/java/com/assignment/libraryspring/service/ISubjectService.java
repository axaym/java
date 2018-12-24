package com.assignment.libraryspring.service;

import java.util.Scanner;

public interface ISubjectService {

	/**
	 * Search subjects by subtitle keyword
	 * 
	 * @param scanner
	 */
	void searchSubject(Scanner scanner);

	/**
	 * add a new subject
	 * 
	 * @param scanner
	 */
	void addSubject(Scanner scanner);

	/**
	 * delete subject by subject_id. related books will be automatically deleted due
	 * to foreign key cascade referential integrity
	 * 
	 * @param scanner
	 */
	void deleteSubject(Scanner scanner);

	/**
	 * sort Subject By  SubjectTitle
	 * @param scanner
	 */
	void sortSubjectBySubjectTitle(Scanner scanner);

}