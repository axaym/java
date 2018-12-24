package com.assignment.library;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.assignment.library.service.IBookService;
import com.assignment.library.service.ISubjectService;

/**
 * library app to perform various operations on book and subject
 *
 */
public class App 
{
	private static ISubjectService subjectService;
	
	private static IBookService bookService;
	
	public static void main( String[] args )
    {    	
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
		if(!scanner.hasNextInt()) {
			selection = 99;
		}
		else {
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

	private static void tryAgain(Scanner scanner) {
		scanner.nextInt();
		
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
    	System.out.println("Press 7 to Exit");
    	System.out.println("****************************************************");
	}
}
