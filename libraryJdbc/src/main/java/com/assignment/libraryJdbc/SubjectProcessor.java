package com.assignment.libraryJdbc;

import java.util.List;
import java.util.Scanner;

import com.assignment.libraryJdbc.dao.ISubjectDAO;
import com.assignment.libraryJdbc.dao.SubjectDAO;
import com.assignment.libraryJdbc.entities.Subject;

public class SubjectProcessor extends Processor {

	/**
	 * Search subjects by subtitle keyword
	 * 
	 * @param scanner
	 */
	public static void searchSubject(Scanner scanner) {
		System.out.println("enter the keyword for subject subtitle to search");
		String subjectName = scanner.next();
		ISubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.searchSubject(subjectName);
		System.out.println("search results for keyword: " + subjectName);
		if (!subjects.isEmpty()) {
			for (Subject subject : subjects) {
				System.out.println("***********************");
				System.out.println(subject.toString());
				System.out.println("***********************");
			}
		} else {
			System.out.println("subjects not found with this title: " + subjectName + ". Please modify your search.");
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
		subject.setDurationInHours(scanner.nextInt());

		ISubjectDAO subjectDAO = new SubjectDAO();
		boolean subjectAdded = false;
		subjectAdded = subjectDAO.addSubject(subject);
		if (subjectAdded) {
			System.out.println("New subject successfully added.");
		} else {
			System.out.println("Error adding new subject.");
		}
	}

	/**
	 * delete subject by subject_id. related books will be automatically deleted due
	 * to foreign key cascade referential integrity
	 * 
	 * @param scanner
	 */
	public static void deleteSubject(Scanner scanner) {
		System.out.println("enter the subject Id to delete");
		long subjectId = scanner.nextLong();
		boolean subjectFoundForDeletion = false;

		ISubjectDAO subjectDAO = new SubjectDAO();
		subjectFoundForDeletion = subjectDAO.deleteSubject(subjectId);

		if (subjectFoundForDeletion) {
			System.out.println("Subject with subjectId: " + subjectId + " successfully deleted.");
		} else {
			System.out.println("No subject found with the subjectId: " + subjectId + " for deletion.");
		}
	}

}
