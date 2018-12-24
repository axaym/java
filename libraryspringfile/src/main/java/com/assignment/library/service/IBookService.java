package com.assignment.library.service;

import java.util.List;
import java.util.Scanner;

import com.assignment.library.entities.Book;

public interface IBookService {

	List<Book> getAllBooks();

	/**
	 * @param scanner
	 */
	void searchBook(Scanner scanner);

	/**
	 * @param scanner
	 */
	void addBook(Scanner scanner);

	void deleteBook(Scanner scanner);

}