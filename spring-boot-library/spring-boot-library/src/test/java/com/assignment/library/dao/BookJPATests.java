package com.assignment.library.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.assignment.library.dao.BookJpaRepository;
import com.assignment.library.entities.Book;

@WebMvcTest
class BookJPATests {

	@Autowired
	private BookJpaRepository bookJpaRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAllBooks() {
		Book book1 = getBook(523, 4, "New book1", "12.34", 3, new Date());
		bookJpaRepository.saveAndFlush(book1);
	}
	
	private Book getBook(int bookId, int subjectId, String title, 
			String price, int volume, Date publishDate) {
	Book book1 = new Book();
	book1.setBookId(bookId);
	book1.setSubjectId(subjectId);
	book1.setTitle(title);
	book1.setPrice(new BigDecimal(price));
	book1.setVolume(volume);
	book1.setPublishDate(publishDate);
	return book1;
}

}
