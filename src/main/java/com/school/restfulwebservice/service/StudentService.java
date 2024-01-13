package com.school.restfulwebservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.restfulwebservice.jpa.StudentJpaRepository;

@Service
public class StudentService {
	StudentJpaRepository studentJpaRepository;
	
	@Autowired
	public StudentService(StudentJpaRepository studentJpaRepository) {
		this.studentJpaRepository = studentJpaRepository;
	}
	
	
}
