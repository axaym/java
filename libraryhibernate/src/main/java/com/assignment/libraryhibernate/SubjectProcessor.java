package com.assignment.libraryhibernate;

import java.util.List;
import java.util.Scanner;

import com.assignment.libraryhibernate.entities.Subject;
import com.assignment.libraryhibernate.hb.SubjectDAO;

public class SubjectProcessor extends Processor {

	/**
	 * Search subjects by subtitle keyword
	 * 
	 * @param scanner
	 */
	public static void searchSubject(Scanner scanner) {
		System.out.println("enter the keyword for subject subtitle to search");
		String subjectName = scanner.next();
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.searchSubjects(subjectName);
		System.out.println("search results for keyword: " + subjectName);
		if (!subjects.isEmpty()) {
			displaySubjects(subjects);
		} else {
			System.out.println("subjects not found with this title: " + subjectName + ". Please modify your search.");
		}

	}

	/**
	 * @param subjects
	 */
	private static void displaySubjects(List<Subject> subjects) {
		for (Subject subject : subjects) {
			System.out.println("***********************");
			System.out.println(subject.toString());
			System.out.println("***********************");
		}
	}

	/**
	 * add a new subject
	 * 
	 * @param scanner
	 */
	public static void addSubject(Scanner scanner) {
		Subject subject = new Subject();

		System.out.println("Enter subject subtitle");
		subject.setSubtitle(scanner.next());

		System.out.println("Enter subject duration in hours");
		subject.setDuration(scanner.nextInt());

		SubjectDAO subjectDAO = new SubjectDAO();
		subjectDAO.persist(subject);
		
	}

	/**
	 * delete subject by subject_id. related books will be automatically deleted due
	 * to foreign key cascade referential integrity
	 * 
	 * @param scanner
	 */
	public static void deleteSubject(Scanner scanner) {
		System.out.println("enter the subject Id to delete");
		int subjectId = scanner.nextInt();

		SubjectDAO subjectDAO = new SubjectDAO();
		subjectDAO.deleteById(subjectId);

	}

	

}
