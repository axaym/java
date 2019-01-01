package com.assignment.library.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.entities.Book;

@Service("bookService")
public class BookService extends BaseService implements IBookService {

	@Autowired
	private ISubjectService subjectService;

	private final String BOOKS_TXT = "books.txt";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.library.service.IBookService#getAllBooks()
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<Book> books = new ArrayList<Book>();
		try {
			File newFile = new File(BOOKS_TXT);
			newFile.createNewFile();

			if (isFileEmpty(BOOKS_TXT)) {
				return books;
			}

			fis = new FileInputStream(new File(BOOKS_TXT));
			ois = new ObjectInputStream(fis);
			books = (List<Book>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return books;
		} catch (IOException e) {
			e.printStackTrace();
			return books;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#searchBook(java.util.Scanner)
	 */
	public List<Book> searchBook(Book bookForSearch) {
		System.out.println("enter the book name to search");
		String bookName = bookForSearch.getTitle();
		List<Book> booksResult = new ArrayList<>();
		List<Book> books = getAllBooks();
		for (Book book : books) {
			if (book.getTitle().contains(bookName)) {
				booksResult.add(book);
			}
		}
		return booksResult;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.library.service.IBookService#addBook(java.util.Scanner)
	 */
	public void addBook(Scanner scanner) {
		Book book = new Book();
		System.out.println("Enter book title");
		book.setTitle(scanner.next());
		System.out.println("Enter book price");
		book.setPrice(scanner.nextDouble());
		System.out.println("Enter book volume");
		book.setVolume(scanner.nextInt());
		System.out.println("Enter book publish date (MM/dd/yyyy)");
		String dateStr = scanner.next();
		System.out.println("dateStr" + dateStr);

		try {
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateStr);
			book.setPublishDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		book.setBookId(getId());
		List<Book> books = getAllBooks();
		books.add(book);
		writeBooksToFile(books);
		System.out.println("Book added");
	}

	/**
	 * @param books
	 */
	private void writeBooksToFile(List<Book> books) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File(BOOKS_TXT));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(books);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#deleteBook(java.util.Scanner)
	 */
	public void deleteBook(Scanner scanner) {
		System.out.println("enter the book name to delete");
		String bookName = scanner.next();
		boolean bookFoundForDeletion = false;
		Book bookForDeletion = null;
		List<Book> books = getAllBooks();
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(bookName)) {
				System.out.println("deleting book: " + book.toString());
				bookForDeletion = book;
				bookFoundForDeletion = true;
				break;
			}
		}
		if (bookFoundForDeletion) {
			books.remove(bookForDeletion);
			writeBooksToFile(books);
		} else {
			System.out.println("No book found with the name: " + bookName + " for deletion.");
		}

		// delete related subject entries
		subjectService.removeRefSubjects(bookForDeletion);
	}

}
