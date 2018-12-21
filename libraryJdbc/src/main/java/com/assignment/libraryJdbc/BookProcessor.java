package com.assignment.libraryJdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.assignment.libraryJdbc.dao.BookDAO;
import com.assignment.libraryJdbc.dao.IBookDAO;
import com.assignment.libraryJdbc.entities.Book;


public class BookProcessor extends Processor {

	/**
	 * @param scanner
	 */
	public static void searchBook(Scanner scanner) {
		System.out.println("enter the keyword for book title to search");
		String bookName = scanner.next();
		IBookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.searchBook(bookName);
		System.out.println("search results for keyword: "+bookName);
		if(!books.isEmpty()) {
			for (Book book : books) {
				System.out.println("***********************");
				System.out.println(book.toString());
				System.out.println("***********************");
			}
		}
		else {
			System.out.println("books not found with this title: "+bookName+". Please modify your search.");
		}
	}

	/**
	 * @param scanner
	 */
	public static void addBook(Scanner scanner) {
		Book book = new Book();
		System.out.println("Enter book title");
		book.setTitle(scanner.next());
		System.out.println("Enter book price");
		book.setPrice(scanner.nextDouble());
		System.out.println("Enter book volume");
		book.setVolume(scanner.nextInt());
		System.out.println("Enter book publish date (MM/dd/yyyy)");
		String dateStr = scanner.next();
		System.out.println("dateStr"+dateStr);
		
		
		try {			
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateStr);
			book.setPublishDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Enter subjectId for this book");
		book.setSubjectId(scanner.nextLong());

		//book.setBookId(getId());
		IBookDAO bookDAO = new BookDAO();
		boolean bookAdded = false;
		bookAdded = bookDAO.addBook(book);
		if(bookAdded) {
			System.out.println("New book successfully added.");
		}
		else {
			System.out.println("Error adding new book.");
		}
	}

	public static void deleteBook(Scanner scanner) {
		System.out.println("enter the book Id to delete");
		long bookId = scanner.nextLong();
		boolean bookFoundForDeletion = false;
		
		IBookDAO bookDAO = new BookDAO();
		bookFoundForDeletion = bookDAO.deleteBook(bookId);
		
		if(bookFoundForDeletion) {
			System.out.println("Book with bookId: "+bookId+" successfully deleted.");
		}
		else {
			System.out.println("No book found with the bookId: "+bookId+" for deletion.");
		}
		
		
	}

	
}
