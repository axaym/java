package com.assignment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.dao.BookJpaRepository;
import com.assignment.library.entities.Book;

@Service("bookService")
public class BookService implements IBookService {
	
	@Autowired
	private BookJpaRepository bookJpaRepository;
	
	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#searchBook(com.assignment.library.entities.Book)
	 */
	@Override
	public List<Book> searchBook(Book book) {
		System.out.println("enter the keyword for book title to search");
		String bookName = book.getTitle();
		List<Book> books = bookJpaRepository.findByTitleLike("%"+bookName+"%");
		System.out.println("search results for keyword: " + bookName);
		if (!books.isEmpty()) {
			return books;
		} else {
			System.out.println("books not found with this title: " + bookName + ". Please modify your search.");
		}
		return books;
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#addBook(com.assignment.library.entities.Book)
	 */
	@Override
	public String addBook(Book book) {
		bookJpaRepository.saveAndFlush(book);
		return "success";
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.IBookService#deleteBook(com.assignment.library.entities.Book)
	 */
	@Override
	public String deleteBook(Book book) {
		System.out.println("enter the book Id to delete");
		int bookId = book.getBookId();

		bookJpaRepository.deleteById(bookId);
		return "success";
	}

}
