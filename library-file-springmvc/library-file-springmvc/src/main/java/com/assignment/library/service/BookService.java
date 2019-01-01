package com.assignment.library.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.library.entities.Book;
import com.assignment.library.entities.Subject;

@Service("bookService")
public class BookService extends BaseService implements IBookService {

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
			File newFile = getFile(BOOKS_TXT);

			fis = new FileInputStream(newFile);
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
	 * com.assignment.library.service.IBookService#searchBook(com.assignment.library
	 * .entities.Book)
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
	 * @see
	 * com.assignment.library.service.IBookService#addBook(com.assignment.library.
	 * entities.Book)
	 */
	public String addBook(Book book) {
		book.setBookId(getId());
		List<Book> books = getAllBooks();
		books.add(book);
		writeBooksToFile(books);
		return "success";
	}

	/**
	 * @param books
	 */
	private void writeBooksToFile(List<Book> books) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File newFile = getFile(BOOKS_TXT);
			fos = new FileOutputStream(newFile);
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
	 * com.assignment.library.service.IBookService#deleteBook(com.assignment.library
	 * .entities.Book)
	 */
	public String deleteBook(Book book) {
		long bookId = book.getBookId();
		boolean bookFoundForDeletion = false;
		Book bookForDeletion = null;
		List<Book> books = getAllBooks();
		for (Book book2 : books) {
			if (book2.getBookId() == bookId) {
				System.out.println("deleting book: " + book2.toString());
				bookForDeletion = book2;
				bookFoundForDeletion = true;
				break;
			}
		}
		if (bookFoundForDeletion) {
			books.remove(bookForDeletion);
			writeBooksToFile(books);
		} else {
			return "No book found with the name: " + bookId + " for deletion.";
		}

		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.IBookService#removeRefBooks(com.assignment.
	 * library.entities.Subject)
	 */
	@Override
	public void removeRefBooks(Subject subject) {
		List<Book> books = getAllBooks();
		List<Book> booksToDelete = new ArrayList<>();

		for (Book book : books) {
			if (book.getSubjectId() == subject.getSubjectId()) {
				booksToDelete.add(book);
			}
		}

		books.removeAll(booksToDelete);
		writeBooksToFile(books);
	}

}
