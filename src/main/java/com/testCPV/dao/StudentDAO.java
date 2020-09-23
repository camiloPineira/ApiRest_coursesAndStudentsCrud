package com.testCPV.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testCPV.entitys.Student;

/*
*  the following interface extends from another interface 
*  called 'JpaRepository', which allows to do all CRUD functions 
*  with the entity 'Student'
*/

public interface StudentDAO extends JpaRepository<Student, String>{

	
}
