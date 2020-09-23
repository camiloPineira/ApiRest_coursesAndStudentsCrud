package com.testCPV.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testCPV.dao.CourseDAO;
import com.testCPV.entitys.Course;


/*
 * From here, the rest services that the API will have are generated. 
 * The default URL for this rest services is '/courses'
 */

@RestController
@RequestMapping("courses")
public class CourseREST {

	@Autowired
	private CourseDAO courseDAO;
	
	
	/*
	 *  The following rest service responds all the courses
	 *  in the table 'courses' 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Course>> getCourses(){
		List<Course> courses = courseDAO.findAll();
		return ResponseEntity.ok(courses);	
	}
	
	
	/*
	 *	The following rest service delivers the data from the course code delivered in the URL 
	 *	If not found, returns 404 status
	 */
	@RequestMapping(value="{courseCode}")
	public ResponseEntity<Course> getCourseByCode(@PathVariable("courseCode") String courseCode){
		Optional<Course> optionalCourse = courseDAO.findById(courseCode);
		if (optionalCourse.isPresent()) {
			return ResponseEntity.ok(optionalCourse.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	/*
	 *  the following rest service will generate 
	 *  a new course with the data from the JSON
	 *  Before adding it, it is verified that the course code has a maximum of 4 characters
	 */
	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course){
		if (course.getCode().length() < 5) {
			courseDAO.save(course);
			return ResponseEntity.created(null).build();
		} else return ResponseEntity.badRequest().build();
	}
	
	
	/*
	 *  The following rest service updates the course data
	 *  indicated in the URL with the data provided in the JSON.
	 *  It's also validated that the new code has a maximum of 4 characters
	 */
	@PutMapping(value="{courseCode}")
	public ResponseEntity<Course> updateCourse(@PathVariable("courseCode") String courseCode,@RequestBody Course course){
		Optional<Course> optionalCourse = courseDAO.findById(courseCode);
		if (optionalCourse.isPresent() && courseCode.length() < 5) {
			Course updateCourse = optionalCourse.get();
			updateCourse.setName(course.getName());
			courseDAO.save(updateCourse);
			return ResponseEntity.ok(updateCourse);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	/*
	 *  The following rest service removes the course indicated in the URL from the SQL table 'courses'
	 */
	@DeleteMapping(value="{courseCode}")
	public ResponseEntity<Void> deleteCourse(@PathVariable("courseCode") String courseCode){
		Optional<Course> optionalCourse = courseDAO.findById(courseCode);
		if (optionalCourse.isPresent()) {
			courseDAO.deleteById(courseCode);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
