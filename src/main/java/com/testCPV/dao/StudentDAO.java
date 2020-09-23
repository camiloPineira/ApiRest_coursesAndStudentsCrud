package com.testCPV.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testCPV.entitys.Student;

public interface StudentDAO extends JpaRepository<Student, String>{

}
