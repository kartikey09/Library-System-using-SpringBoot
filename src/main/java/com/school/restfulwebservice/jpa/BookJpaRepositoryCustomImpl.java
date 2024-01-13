package com.school.restfulwebservice.jpa;

import java.util.List;
import org.springframework.stereotype.Component;

import com.school.restfulwebservice.book.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class BookJpaRepositoryCustomImpl implements BookJpaRepositoryCustom{

	 @PersistenceContext
	    private EntityManager em;
	 
	@Override
	public List<Book> findByGenre(String genreToFind) {
		String q = "SELECT b FROM Book b WHERE b.genre = ?1";
		TypedQuery<Book> query = em.createQuery(q, Book.class);
		query.setParameter(1, genreToFind);
		return query.getResultList();
	}
}
