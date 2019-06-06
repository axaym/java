
package com.assignment.library.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.library.dao.BookJpaRepository;
import com.assignment.library.entities.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDAOTest {

	@Autowired
	private BookJpaRepository bookJpaRepository;

	@Test
	public void testSaveAndFindBook() throws Exception {
		Book book1 = getBook(523, 4, "New book1", "12.34", 3, new Date());
		bookJpaRepository.saveAndFlush(book1);

		List<Book> books = bookJpaRepository.findByTitle(book1.getTitle());

		Assertions.assertEquals(book1.getTitle(), books.get(0).getTitle());
		Assertions.assertEquals(book1.getVolume(), books.get(0).getVolume());
		
	}
	
	@Test
	public void testDeleteAndFindBook() throws Exception {
		Book book1 = getBook(527, 4, "New bookfor Delete test", "9.12", 3, new Date());
		bookJpaRepository.saveAndFlush(book1);
		List<Book> books = bookJpaRepository.findByTitle(book1.getTitle());
		bookJpaRepository.deleteById(books.get(0).getBookId());

		List<Book> booksAfterDeletion = bookJpaRepository.findByTitle(book1.getTitle());
		Assertions.assertEquals(0, booksAfterDeletion.size());
	}

	private Book getBook(int bookId, int subjectId, String title, String price, int volume, Date publishDate) {
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
