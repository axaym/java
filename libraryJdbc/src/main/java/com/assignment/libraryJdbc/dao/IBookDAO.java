package com.assignment.libraryJdbc.dao;

import java.util.List;

import com.assignment.libraryJdbc.entities.Book;

public interface IBookDAO {

	/**
	 * search book by book title keyword
	 * 
	 * @param bookName
	 * @return
	 */
	public List<Book> searchBook(String bookName);

	/**
	 * add a new book
	 * 
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book);

	/**
	 * delete book by book_id
	 * 
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(long bookId);

	/**
	 * get books
	 * @return
	 */
	public List<Book> getBooks();

}
