package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.entities.Book;
import com.assignment.library.service.IBookService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("bookController")
public class BookController {

	@Autowired
	private IBookService bookService;
		
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/book/search")
    public @ResponseBody List<Book> searchBooks(@RequestBody Book book) {
        return bookService.searchBook(book);
    }
	
	@GetMapping("/book/books")
    public @ResponseBody List<Book> getAllBooks() {
        return bookService.getAllBooks();        
    }
	
	@GetMapping("/book/{bookId}")
    public @ResponseBody List<Book> getBookById(@PathVariable Integer bookId) {
		/*String pass = new BCryptPasswordEncoder().encode("pass");
		System.out.println("pass: "+pass);*/
        return bookService.getBookById(bookId);
    }
	
	@GetMapping("/book/count")
    public @ResponseBody long getBookCount() {
        return bookService.getBookCount();
    }
	
	@PutMapping("/book/update")
    public @ResponseBody String updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/book/search/title")
    public @ResponseBody List<Book> searchBooksByTitle(@RequestBody Book book) {
        return bookService.searchBookByTitleEqual(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/book/add")
    public @ResponseBody String addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
	
	/**
	 * @param book
	 * @return
	 */
	@PostMapping("/book/delete")
    public @ResponseBody String deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }
}
