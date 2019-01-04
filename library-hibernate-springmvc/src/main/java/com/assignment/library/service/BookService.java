package com.assignment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.dao.IBookDAO;
import com.assignment.library.dao.ISubjectDAO;
import com.assignment.library.entities.Book;
import com.assignment.library.entities.Subject;

@Service("bookService")
public class BookService implements IBookService {
	
	@Autowired
	private IBookDAO bookDAO;
	
	@Autowired
	private ISubjectDAO subjectDAO;

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#searchBook(com.assignment.library.entities.Book)
	 */
	@Override
	public List<Book> searchBook(Book book) {
		System.out.println("enter the keyword for book title to search");
		String bookName = book.getTitle();
		List<Book> books = bookDAO.searchBooks(bookName);
		System.out.println("search results for keyword: " + bookName);
		if (!books.isEmpty()) {
			return books;
		} else {
			System.out.println("books not found with this title: " + bookName + ". Please modify your search.");
		}
		return books;
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#addBook(com.assignment.library.entities.Book)
	 */
	@Override
	public String addBook(Book book) {

		Subject subject = subjectDAO.findById(book.getSubjectId());
		book.setSubject(subject);

		bookDAO.persist(book);
		return "success";
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#deleteBook(com.assignment.library.entities.Book)
	 */
	@Override
	public String deleteBook(Book book) {
		System.out.println("enter the book Id to delete");
		int bookId = book.getBookId();

		bookDAO.deleteById(bookId);
		return "success";
	}

}
