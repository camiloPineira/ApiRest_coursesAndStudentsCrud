package com.testCPV.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*  
 *  
 *  Here the class 'Course' is initialized as entity
 *  and in which table it will be stored, (in this example, in the table 'courses').
 *  In addition, the fields and constructors of the entity/table are generated
 *
 */
@Entity
@Table(name="courses")
public class Course {
	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="name",nullable=false)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
