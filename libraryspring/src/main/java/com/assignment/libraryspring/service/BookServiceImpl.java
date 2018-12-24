package com.assignment.libraryspring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.libraryspring.dao.IBookDAO;
import com.assignment.libraryspring.dao.ISubjectDAO;
import com.assignment.libraryspring.entities.Book;
import com.assignment.libraryspring.entities.Subject;

@Service("bookService")
public class BookServiceImpl extends BaseService implements IBookService {

	@Autowired
	private IBookDAO bookDAO;

	@Autowired
	private ISubjectDAO subjectDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.service.IBookService#searchBook(java.util.
	 * Scanner)
	 */
	public void searchBook(Scanner scanner) {
		System.out.println("enter the keyword for book title to search");
		String bookName = scanner.next();

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
	private void displayBooks(List<Book> books) {
		for (Book book : books) {
			System.out.println("***********************");
			System.out.println(book.toString());
			System.out.println("***********************");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryhibernate.service.IBookService#addBook(java.util.
	 * Scanner)
	 */
	public void addBook(Scanner scanner) {
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
		Subject subject = subjectDAO.findById(subjectId);
		book.setSubject(subject);

		bookDAO.persist(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.service.IBookService#deleteBook(java.util.
	 * Scanner)
	 */
	public void deleteBook(Scanner scanner) {
		System.out.println("enter the book Id to delete");
		int bookId = scanner.nextInt();

		bookDAO.deleteById(bookId);
	}

	@Override
	public void sortBookByTitle(Scanner scanner) {
		List<Book> books = bookDAO.getBooks();
		System.out.println("sorted Books By Title: ");
		if (!books.isEmpty()) {			
			List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book :: getTitle)).collect(Collectors.toList());
			displayBooks(sortedBooks);
		}
		
	}

	@Override
	public void sortBookByPublishDate(Scanner scanner) {
		List<Book> books = bookDAO.getBooks();
		System.out.println("sorted Books By PublishDate: ");
		if (!books.isEmpty()) {			
			List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book :: getPublishDate)).collect(Collectors.toList());
			displayBooks(sortedBooks);
		}		
	}

}
