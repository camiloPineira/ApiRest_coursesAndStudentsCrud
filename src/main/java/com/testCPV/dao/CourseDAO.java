package com.testCPV.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testCPV.entitys.Course;


/*
*  the following interface extends from another interface 
*  called 'JpaRepository', which allows to do all CRUD functions 
*  with the entity 'Course'
*/

public interface CourseDAO extends JpaRepository<Course, String> {
	
	 
}
