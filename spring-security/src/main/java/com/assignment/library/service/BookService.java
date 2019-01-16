package com.assignment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.assignment.library.dao.BookJpaRepository;
import com.assignment.library.entities.Book;

@Service("bookService")
public class BookService implements IBookService {

	@Autowired
	private BookJpaRepository bookJpaRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#searchBook(com.assignment.library
	 * .entities.Book)
	 */
	@Override
	public List<Book> searchBook(Book book) {
		System.out.println("enter the keyword for book title to search");
		String bookName = book.getTitle();
		List<Book> books = bookJpaRepository.findByTitleLike("%" + bookName + "%");
		System.out.println("search results for keyword: " + bookName);
		if (!books.isEmpty()) {
			return books;
		} else {
			System.out.println("books not found with this title: " + bookName + ". Please modify your search.");
		}
		return books;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#addBook(com.assignment.library.
	 * entities.Book)
	 */
	@Override
	public String addBook(Book book) {
		try {
			bookJpaRepository.saveAndFlush(book);
		}
		catch (Exception e) {
			return "book cannot be added. Please check the input request.";
		}
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#deleteBook(com.assignment.library
	 * .entities.Book)
	 */
	@Override
	public String deleteBook(Book book) {
		int bookId = book.getBookId();
		try {
			bookJpaRepository.deleteById(bookId);
		} catch (EmptyResultDataAccessException e) {
			return "bookId: " + bookId + " not found to delete";
		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}

	@Override
	public List<Book> searchBookByTitleEqual(Book book) {
		return bookJpaRepository.findByTitle(book.getTitle());
	}

	@Override
	public List<Book> getAllBooks() {
		return bookJpaRepository.findAll();
	}

	@Override
	public List<Book> getBookById(Integer bookId) {
		return bookJpaRepository.findByBookId(bookId);
	}

	@Override
	public String updateBook(Book book) {
		Integer bookId = book.getBookId();
		if (bookJpaRepository.existsById(bookId)) {
			bookJpaRepository.saveAndFlush(book);
			return "bookId: " + bookId + " successfully updated";
		} else {
			return "bookId: " + bookId + " does not exist to update";
		}
	}

	@Override
	public long getBookCount() {
		return bookJpaRepository.count();
	}

}
