package com.assignment.library.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.library.entities.Book;

public interface IBookService {

	public List<Book> getAllBooks();

	/**
	 * @param scanner
	 */
	public List<Book> searchBook(Book book);

	/**
	 * @param scanner
	 */
	public void addBook(Scanner scanner);

	public void deleteBook(Scanner scanner);

}