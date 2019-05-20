package com.assignment.library.service;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.library.controller.BookController;
import com.assignment.library.entities.Book;

@ContextConfiguration(classes = { BookController.class })
@WebMvcTest
@AutoConfigureMockMvc
public class SpringBootLibraryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IBookService bookService;

	@Test
	public void testGetBooks() throws Exception {
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

		when(bookService.getAllBooks()).thenReturn(books);
		mockMvc.perform(MockMvcRequestBuilders.get("/book/books").with(user("axay"))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
