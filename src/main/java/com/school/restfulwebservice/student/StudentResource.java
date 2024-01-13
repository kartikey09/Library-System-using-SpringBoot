package com.school.restfulwebservice.student;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.school.restfulwebservice.jpa.StudentJpaRepository;

import jakarta.validation.Valid;

@RestController
public class StudentResource {
	
	StudentJpaRepository studentJpaRepository;
	
	@Autowired
	public StudentResource(StudentJpaRepository studentJpaRepository) {
		this.studentJpaRepository = studentJpaRepository;
	}
	
	@GetMapping(path = "/students")
	public MappingJacksonValue getAllStudents() {
		List<Student> listOfStudents = studentJpaRepository.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "lastName");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(listOfStudents);
		FilterProvider filters = new SimpleFilterProvider().addFilter("StudentFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;	
	}
	
	@PostMapping(path = "/students")
	public ResponseEntity<Student> addNewUser(@Valid @RequestBody Student student) {
		Student savedStudent = studentJpaRepository.save(student);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedStudent.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
