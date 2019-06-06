package com.assignment.library.service;

import java.util.List;
import java.util.Optional;

import com.assignment.library.entities.Book;

public interface IBookService {

	/**
	 * @param book
	 * @return
	 */
	public List<Book> searchBook(Book book);
	
	/**
	 * @param book
	 * @return
	 */
	public List<Book> searchBookByTitleEqual(Book book);

	/**
	 * @param book
	 */
	public String addBook(Book book);

	/**
	 * @param book
	 */
	public String deleteBook(Book book);

	/**
	 * @return
	 */
	public List<Book> getAllBooks();

	/**
	 * @param bookId
	 * @return
	 */
	public List<Book> getBookById(Integer bookId);

	public String updateBook(Book book);

	public long getBookCount();

}