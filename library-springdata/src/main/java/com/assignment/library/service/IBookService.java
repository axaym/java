package com.assignment.library.service;

import java.util.List;

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

}