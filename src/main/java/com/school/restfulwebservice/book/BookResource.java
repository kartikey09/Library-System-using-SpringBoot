package com.school.restfulwebservice.book;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.school.restfulwebservice.jpa.BookJpaRepository;
import com.school.restfulwebservice.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookResource {
	
	private BookJpaRepository bookJpaRepository;
	
	private BookService bookService;
	
	@Autowired
	public BookResource(BookJpaRepository bookJpaRepository, BookService bookService) {
		this.bookJpaRepository = bookJpaRepository;
		this.bookService = bookService;
	}
	
	@PostMapping(path = "/books")
	public void saveNewBook(@Valid @RequestBody Book book) {
		bookService.toLowerAll(book);
		bookJpaRepository.save(book);
	}
	
	@GetMapping(path = "/books")
	public List<Book> retrieveAllbooks(){
		System.out.println("here");
		return bookJpaRepository.findAll();
	}
	
	@GetMapping(path="/books/genre/{genre}")
	public List<Book> getBooksByGenre(@PathVariable String genre){
		List<Book> list = bookJpaRepository.findByGenre(genre);
		return list;
	}
	
	@GetMapping(path="/books/{bookName}")
	public List<Book> getBookByName(@PathVariable String bookName){
		return bookJpaRepository.findByBookName(bookName);
	}
	
	@GetMapping(path="/books/genre-list")
	public List<?> getAllGenre() {
		return bookService.getListOfGenres();
	}
	
	@DeleteMapping(path="/books/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable UUID id) {
		Optional<Book> bookToBeDeleted = bookJpaRepository.findById(id);
		if(bookToBeDeleted.isEmpty() || bookToBeDeleted.get() == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		
		bookJpaRepository.deleteById(id);
		return new ResponseEntity<Book>(HttpStatus.OK);
	}
	
	@GetMapping(path="/books/stats")
	public Map<String, Long> getBookStats() {
		return bookService.getBookStatsAsMap();
	}
	
//	@GetMapping(path = "/books/available")
//	public MappingJacksonValue getAvailableBooks(){
//		List<Book> listOfBooks = bookJpaRepository.getAllBooks();
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("bookName", "authorName", "genre");
//		return genericfilter(listOfBooks, filter);
//		
//	}
}
