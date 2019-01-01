package com.assignment.library.service;

import java.util.List;

import com.assignment.library.entities.Book;
import com.assignment.library.entities.Subject;

public interface IBookService {

	/**
	 * @return
	 */
	public List<Book> getAllBooks();

	/**
	 * @param book
	 * @return
	 */
	public List<Book> searchBook(Book book);

	/**
	 * @param book
	 * @return
	 */
	public String addBook(Book book);

	/**
	 * @param book
	 * @return
	 */
	public String deleteBook(Book book);

	/**
	 * @param subject
	 */
	public void removeRefBooks(Subject subject);

}