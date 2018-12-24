package com.assignment.libraryspring;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.assignment.libraryspring.service.IBookService;
import com.assignment.libraryspring.service.ISubjectService;

/**
 * library app to perform various operations on book and subject
 *
 */
public class App {
	
	
	private static ISubjectService subjectService;
	
	private static IBookService bookService;
	
	public static void main(String[] args) {
		
		ApplicationContext appContext =
				new AnnotationConfigApplicationContext(AppConfig.class);
		bookService = appContext.getBean("bookService", IBookService.class);
		subjectService = appContext.getBean("subjectService", ISubjectService.class);
		Scanner scanner = new Scanner(System.in);
		getUserInput(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void getUserInput(Scanner scanner) {
		displaySelections();
		int selection = 99;
		if (!scanner.hasNextInt()) {
			selection = 99;
		} else {
			selection = scanner.nextInt();
		}
		switch (selection) {
		case 1:
			subjectService.addSubject(scanner);
			getUserInput(scanner);
			break;

		case 2:
			bookService.addBook(scanner);
			getUserInput(scanner);
			break;

		case 3:
			subjectService.deleteSubject(scanner);
			getUserInput(scanner);
			break;

		case 4:
			bookService.deleteBook(scanner);
			getUserInput(scanner);
			break;

		case 5:
			bookService.searchBook(scanner);
			getUserInput(scanner);
			break;

		case 6:
			subjectService.searchSubject(scanner);
			getUserInput(scanner);
			break;
		
		case 7:
			bookService.sortBookByTitle(scanner);
			getUserInput(scanner);
			break;

		case 8:
			subjectService.sortSubjectBySubjectTitle(scanner);
			getUserInput(scanner);
			break;

		case 9:
			bookService.sortBookByPublishDate(scanner);
			getUserInput(scanner);
			break;

		case 10:
			System.out.println("Exiting... rerun program to start over.");
			scanner.close();
			break;


		default:
			System.out.println("invalid option selected. Please select a number to choose an option. ");
			System.out.println("Exiting... rerun program to start over.");
			scanner.close();
			break;
		}

	}

	/**
	 * 
	 */
	private static void displaySelections() {
		System.out.println("****************************************************");
		System.out.println("Please enter appropriate number to choose an option:");
		System.out.println("Press 1 to Add a Subject");
		System.out.println("Press 2 to Add a Book");
		System.out.println("Press 3 to Delete a Subject");
		System.out.println("Press 4 to Delete a book");
		System.out.println("Press 5 to Search for a book");
		System.out.println("Press 6 to Search for a subject");
		System.out.println("Press 7 to Sort Book By Title");
		System.out.println("Press 8 to Sort Subject By Subject Title");
		System.out.println("Press 9 to Sort Books by publish Date");
		System.out.println("Press 10 to Exit");
		System.out.println("****************************************************");
	}
}
