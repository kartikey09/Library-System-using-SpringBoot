package com.school.restfulwebservice.book;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.school.restfulwebservice.student.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	@Size(min = 3, message = "Book name cannot be less than 3 characters")
	private String bookName;

	@Size(min = 2, message = "author name cannot be less than 3 characters")
	private String authorName;

	@Size(min = 3, message = "genre name cannot be less than 3 characters")
	private String genre;

	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;

	public Book() {
	}

	public Book(UUID id, String bookName, String authorName, String genre, Student student) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.genre = genre;
		this.student = student;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + ", genre=" + genre
				+ ", student=" + student + "]";
	}

}
