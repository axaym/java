package com.assignment.libraryspring.service;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.libraryspring.dao.ISubjectDAO;
import com.assignment.libraryspring.entities.Subject;

@Service("subjectService")
public class SubjectServiceImpl extends BaseService implements ISubjectService {

	@Autowired
	private ISubjectDAO subjectDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.service.ISubjectService#searchSubject(java.
	 * util.Scanner)
	 */
	public void searchSubject(Scanner scanner) {
		System.out.println("enter the keyword for subject subtitle to search");
		String subjectName = scanner.next();

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
	private void displaySubjects(List<Subject> subjects) {
		for (Subject subject : subjects) {
			System.out.println("***********************");
			System.out.println(subject.toString());
			System.out.println("***********************");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.service.ISubjectService#addSubject(java.util.
	 * Scanner)
	 */
	public void addSubject(Scanner scanner) {
		Subject subject = new Subject();

		System.out.println("Enter subject subtitle");
		subject.setSubtitle(scanner.next());

		System.out.println("Enter subject duration in hours");
		subject.setDuration(scanner.nextInt());

		subjectDAO.persist(subject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.service.ISubjectService#deleteSubject(java.
	 * util.Scanner)
	 */
	public void deleteSubject(Scanner scanner) {
		System.out.println("enter the subject Id to delete");
		int subjectId = scanner.nextInt();
		subjectDAO.deleteById(subjectId);
	}

	@Override
	public void sortSubjectBySubjectTitle(Scanner scanner) {
		List<Subject> subjects = subjectDAO.getSubjects();
		System.out.println("search results for sort subjects by subject title: ");
		if (!subjects.isEmpty()) {
			List<Subject> sortedSubjects = subjects.stream().sorted(Comparator.comparing(Subject::getSubtitle))
					.collect(Collectors.toList());
			displaySubjects(sortedSubjects);
		}
		
	}
	
}
