package com.school.restfulwebservice.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.restfulwebservice.student.Student;

public interface StudentJpaRepository extends JpaRepository<Student, UUID>, StudentJpaRepositoryCustom{

}
