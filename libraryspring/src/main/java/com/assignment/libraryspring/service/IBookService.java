package com.assignment.libraryspring.service;

import java.util.Scanner;

public interface IBookService {

	/**
	 * search book by book title keyword
	 * 
	 * @param scanner
	 */
	void searchBook(Scanner scanner);

	/**
	 * add a new book details. book_id will be auto increment in database.
	 * 
	 * @param scanner
	 */
	void addBook(Scanner scanner);

	/**
	 * delete a book by book_id
	 * 
	 * @param scanner
	 */
	void deleteBook(Scanner scanner);

	/** 
	 * sort Book By Title
	 * @param scanner
	 */
	void sortBookByTitle(Scanner scanner);

	/**
	 * sort Book By PublishDate
	 * @param scanner
	 */
	void sortBookByPublishDate(Scanner scanner);

}