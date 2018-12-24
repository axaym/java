package com.assignment.libraryhibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.assignment.libraryhibernate.entities.Book;
import com.assignment.libraryhibernate.entities.Subject;
import com.assignment.libraryhibernate.hb.BookDAO;
import com.assignment.libraryhibernate.hb.SubjectDAO;

public class BookProcessor extends Processor {

	/**
	 * search book by book title keyword
	 * 
	 * @param scanner
	 */
	public static void searchBook(Scanner scanner) {
		System.out.println("enter the keyword for book title to search");
		String bookName = scanner.next();
		BookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.searchBooks(bookName);
		System.out.println("search results for keyword: " + bookName);
		if (!books.isEmpty()) {
			displayBooks(books);
		} else {
			System.out.println("books not found with this title: " + bookName + ". Please modify your search.");
		}
	}

	/**
	 * @param books
	 */
	private static void displayBooks(List<Book> books) {
		for (Book book : books) {
			System.out.println("***********************");
			System.out.println(book.toString());
			System.out.println("***********************");
		}
	}

	/**
	 * add a new book details. book_id will be auto increment in database.
	 * 
	 * @param scanner
	 */
	public static void addBook(Scanner scanner) {
		Book book = new Book();
		System.out.println("Enter book title");
		book.setTitle(scanner.next());
		System.out.println("Enter book price");
		book.setPrice(scanner.nextBigDecimal());
		System.out.println("Enter book volume");
		book.setVolume(scanner.nextInt());
		System.out.println("Enter book publish date (MM/dd/yyyy)");
		String dateStr = scanner.next();

		try {
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateStr);
			book.setPublishDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Enter subjectId for this book");
		int subjectId = scanner.nextInt();
		SubjectDAO subjectDAO = new SubjectDAO();
		Subject subject = subjectDAO.findById(subjectId);
		book.setSubject(subject);

		BookDAO bookDAO = new BookDAO();
		bookDAO.persist(book);
	}

	/**
	 * delete a book by book_id
	 * 
	 * @param scanner
	 */
	public static void deleteBook(Scanner scanner) {
		System.out.println("enter the book Id to delete");
		int bookId = scanner.nextInt();

		BookDAO bookDAO = new BookDAO();
		bookDAO.deleteById(bookId);
	}

}
