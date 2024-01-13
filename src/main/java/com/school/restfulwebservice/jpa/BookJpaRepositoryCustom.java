package com.school.restfulwebservice.jpa;

import java.util.List;

import com.school.restfulwebservice.book.Book;

public interface BookJpaRepositoryCustom {
	List<Book> findByGenre(String genreToFind);
}
