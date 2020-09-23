package com.testCPV.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testCPV.entitys.Course;



public interface CourseDAO extends JpaRepository<Course, String> {
	
	 
}
