package com.assignment.library.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.library.dao.BookJpaRepository;
import com.assignment.library.entities.Book;

@ContextConfiguration(classes = { BookService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class BookServiceTests {

	@Autowired
	private IBookService bookService;

	@MockBean
	private BookJpaRepository bookJpaRepository;

	@Test
	public void testGetAllBooks() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
		Book book2 = getBook(2, 2, "Chemistry", "22.23", 4);

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		when(bookJpaRepository.findAll()).thenReturn(books);
		List<Book> booksFromService = bookService.getAllBooks();
		Assertions.assertNotNull(booksFromService);
	}
	
	@Test
	public void testSearchBooks() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
		Book book2 = getBook(2, 2, "Chemistry", "22.23", 4);
		
		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		when(bookJpaRepository.findByTitleLike("%" + book1.getTitle() + "%")).thenReturn(books);
		List<Book> booksFromService = bookService.searchBook(book1);
		Assertions.assertNotNull(booksFromService);
	}
	
	@Test
	public void testGetBookById() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
		
		List<Book> books = new ArrayList<>();
		books.add(book1);

		when(bookJpaRepository.findByBookId(book1.getBookId())).thenReturn(books);
		List<Book> booksFromService = bookService.getBookById(book1.getBookId());
		Assertions.assertNotNull(booksFromService);
	}
	
	@Test
	public void testGetBookCount() {
		when(bookJpaRepository.count()).thenReturn((long) 2);
		long count = bookService.getBookCount();
		Assertions.assertEquals(2, count);
	}
	
	@Test
	public void testSearchBookByTitleEqual() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
		List<Book> books = new ArrayList<>();
		books.add(book1);
		
		when(bookJpaRepository.findByTitle(book1.getTitle())).thenReturn(books);
		List<Book> booksReceived = bookService.searchBookByTitleEqual(book1);
		Assertions.assertNotNull(booksReceived);
		Assertions.assertEquals(books, booksReceived);
	}
	
	@Test
	public void tesAddBook() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
				
		when(bookJpaRepository.saveAndFlush(book1)).thenReturn(book1);		
		String status = bookService.addBook(book1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteBook() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
				
		doNothing().when(bookJpaRepository).deleteById(book1.getBookId());
		String status = bookService.deleteBook(book1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testUpdateBook() {
		Book book1 = getBook(1, 2, "Biology", "12.23", 3);
				
		when(bookJpaRepository.existsById(book1.getBookId())).thenReturn(true);
		when(bookJpaRepository.saveAndFlush(book1)).thenReturn(book1);
		String status = bookService.updateBook(book1);
		Assertions.assertEquals("bookId: " + book1.getBookId() + " successfully updated", status);
	}

	private Book getBook(int bookId, int subjectId, String title, String price, int volume) {
		Book book1 = new Book();
		book1.setBookId(bookId);
		book1.setSubjectId(subjectId);
		book1.setTitle(title);
		book1.setPrice(new BigDecimal(price));
		book1.setVolume(volume);
		return book1;
	}
}
