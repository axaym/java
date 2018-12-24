package com.assignment.library.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.library.entities.Book;
import com.assignment.library.entities.Subject;

public interface ISubjectService {

	void searchSubject(Scanner scanner);

	void addSubject(Scanner scanner);

	List<Subject> getAllSubjects();

	void deleteSubject(Scanner scanner);

	void removeRefSubjects(Book bookForDeletion);

}