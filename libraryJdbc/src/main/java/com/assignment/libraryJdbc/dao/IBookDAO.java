package com.assignment.libraryJdbc.dao;

import java.util.List;

import com.assignment.libraryJdbc.entities.Book;

public interface IBookDAO {
	
	public List<Book> searchBook(String bookName);
	
	public boolean addBook(Book book);
	
	public boolean deleteBook(long bookId);

}
