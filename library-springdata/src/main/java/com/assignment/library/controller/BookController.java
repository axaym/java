package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.library.entities.Book;
import com.assignment.library.service.IBookService;

@CrossOrigin(origins="http://localhost:4200")
@Controller("bookController")
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private IBookService bookService;
		
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/search")
    public @ResponseBody List<Book> searchBooks(@RequestBody Book book) {
        return bookService.searchBook(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/search/title")
    public @ResponseBody List<Book> searchBooksByTitle(@RequestBody Book book) {
        return bookService.searchBookByTitleEqual(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/add")
    public @ResponseBody String addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/delete")
    public @ResponseBody String deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }
}
