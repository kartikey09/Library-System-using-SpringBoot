package com.school.restfulwebservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.restfulwebservice.book.Book;
import com.school.restfulwebservice.jpa.BookJpaRepository;

@Service
public class BookService {
	
	BookJpaRepository bookJpaRepository;
	
	@Autowired
	public BookService(BookJpaRepository bookJpaRepository) {
		this.bookJpaRepository = bookJpaRepository;
	}

	public void toLowerAll(Book book) {
		book.setAuthorName(book.getAuthorName().toLowerCase());
		book.setBookName(book.getBookName().toLowerCase());
		book.setGenre(book.getGenre().toLowerCase());
	}
	
	public List<String> getListOfGenres(){
		List<?> allGenres = bookJpaRepository.getAllGenres();
		List<String> listOfGenres = new ArrayList<>(allGenres.size());
		if(allGenres != null && !allGenres.isEmpty())
			for(Object obj : allGenres)
				listOfGenres.add((String)obj);
		return listOfGenres;	
	}
	
	public Map<String, Long> getBookStatsAsMap(){
		List<Object[]> bookStatsAsList = bookJpaRepository.getBookStatsAsList();
		Map<String, Long> result = null;
		if(bookStatsAsList != null && !bookStatsAsList.isEmpty()) {
			result = new HashMap<>();
			for(Object[] ob : bookStatsAsList)
				result.put((String)ob[0], (Long)ob[1]);
		}
		return result;
	}
}
