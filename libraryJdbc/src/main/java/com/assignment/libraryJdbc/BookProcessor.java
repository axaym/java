package com.assignment.libraryJdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.assignment.libraryJdbc.dao.BookDAO;
import com.assignment.libraryJdbc.dao.IBookDAO;
import com.assignment.libraryJdbc.entities.Book;

public class BookProcessor extends Processor {

	/**
	 * search book by book title keyword
	 * 
	 * @param scanner
	 */
	public static void searchBook(Scanner scanner) {
		System.out.println("enter the keyword for book title to search");
		String bookName = scanner.next();
		IBookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.searchBook(bookName);
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
		book.setPrice(scanner.nextDouble());
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
		book.setSubjectId(scanner.nextLong());

		IBookDAO bookDAO = new BookDAO();
		boolean bookAdded = false;
		bookAdded = bookDAO.addBook(book);
		if (bookAdded) {
			System.out.println("New book successfully added.");
		} else {
			System.out.println("Error adding new book.");
		}
	}

	/**
	 * delete a book by book_id
	 * 
	 * @param scanner
	 */
	public static void deleteBook(Scanner scanner) {
		System.out.println("enter the book Id to delete");
		long bookId = scanner.nextLong();
		boolean bookFoundForDeletion = false;

		IBookDAO bookDAO = new BookDAO();
		bookFoundForDeletion = bookDAO.deleteBook(bookId);

		if (bookFoundForDeletion) {
			System.out.println("Book with bookId: " + bookId + " successfully deleted.");
		} else {
			System.out.println("No book found with the bookId: " + bookId + " for deletion.");
		}

	}

	public static void sortBookByTitle(Scanner scanner) {
		IBookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.getBooks();
		System.out.println("sorted Books By Title: ");
		if (!books.isEmpty()) {			
			List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book :: getTitle)).collect(Collectors.toList());
			displayBooks(sortedBooks);
		}		
	}

	public static void sortBookByPublishDate(Scanner scanner) {
		IBookDAO bookDAO = new BookDAO();
		List<Book> books = bookDAO.getBooks();
		System.out.println("sorted Books By PublishDate: ");
		if (!books.isEmpty()) {			
			List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book :: getPublishDate)).collect(Collectors.toList());
			displayBooks(sortedBooks);
		}	
	}

}
