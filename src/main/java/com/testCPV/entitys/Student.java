package com.testCPV.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*  
 *  
 *  Here the class 'Student' is initialized as entity
 *  and in which table it will be stored, (in this example, in the table 'students').
 *  In addition, the fields and constructors of the entity/table are generated
 *
 */
@Entity
@Table(name="students")
public class Student {

	@Id
	@Column(name="rut", nullable=false)
	private String rut;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="lastName",nullable=false)
	private String lastName;
	
	@Column(name="age",nullable=false)
	private short age;
	
	@Column(name="course",nullable=false)
	private String course;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	
}
