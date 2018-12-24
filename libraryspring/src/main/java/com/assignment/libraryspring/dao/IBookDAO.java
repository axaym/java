package com.assignment.libraryspring.dao;

import java.util.List;

import com.assignment.libraryspring.entities.Book;

public interface IBookDAO {

	void persist(Book transientInstance);

	void delete(Book persistentInstance);

	void deleteById(Integer bookId);

	Book findById(java.lang.Integer id);

	List<Book> searchBooks(String partialTitle);
	
	List<Book> getBooks();

}