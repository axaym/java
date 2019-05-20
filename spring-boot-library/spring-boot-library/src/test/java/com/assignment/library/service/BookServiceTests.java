package com.assignment.library.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
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
	public void testGetBooks() throws IOException {
		Book book1 = new Book();
		book1.setBookId(2);
		book1.setSubjectId(3);
		book1.setTitle("ABC");
		book1.setPrice(new BigDecimal("12.22"));
		book1.setVolume(3);

		Book book2 = new Book();
		book2.setBookId(4);
		book2.setSubjectId(4);
		book2.setTitle("Biology");
		book2.setPrice(new BigDecimal("12.34"));
		book2.setVolume(2);

		List<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		when(bookJpaRepository.findAll()).thenReturn(books);
		List<Book> booksFromService = bookService.getAllBooks();
		Assertions.assertNotNull(booksFromService);

	}
}
