package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.entities.Book;
import com.assignment.library.service.IBookService;

@RestController("bookController")
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping("/test")
    public void getTest() {
        System.out.println("Hello world");;
    }
	
	@PostMapping("/search")
    public List<Book> searchBooks(@RequestBody Book book) {
        return bookService.searchBook(book);
    }
}
