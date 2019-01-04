package com.assignment.library.dao;

import java.util.List;

import com.assignment.library.entities.Book;

public interface IBookDAO {

	public void persist(Book transientInstance);

	public void delete(Book persistentInstance);

	public void deleteById(Integer bookId);

	public Book findById(java.lang.Integer id);

	public List<Book> searchBooks(String partialTitle);

}