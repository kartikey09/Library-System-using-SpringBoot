package com.school.restfulwebservice.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.restfulwebservice.book.Book;

public interface BookJpaRepository extends JpaRepository<Book, UUID>, BookJpaRepositoryCustom{
	
	@Query("Select b from Book b where b.bookName like %:bookName%")
    List<Book> findByBookName(@Param("bookName")String bookName);
	
	@Query("Select b.genre from Book b group by b.genre")
	List<?> getAllGenres();
	
	@Query("Select b.bookName, count(b) as count from Book b group by b.bookName")
	List<Object[]> getBookStatsAsList();
	
	@Query("Select b from Book b")
	List<Book> getAllBooks();
}
