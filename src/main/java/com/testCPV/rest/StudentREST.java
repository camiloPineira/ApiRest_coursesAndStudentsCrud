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

import com.testCPV.dao.StudentDAO;
import com.testCPV.entitys.Student;

/*
 * Here, the rest services that the API will have are generated
 * The default URL for this rest services is '/students'
 */
@RestController
@RequestMapping("students")
public class StudentREST {
	
    /*  
     *  This function 'validateRut' will validate the rut of the student and 
	 *  return true if it's a validate rut or false if it's an incorrect rut
	 */
	
	public static boolean validateRut(String rut) {

		boolean validation = false;
		try {
		
		rut =  rut.toUpperCase();
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");
		int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

		char dv = rut.charAt(rut.length() - 1);

		int m = 0, s = 1;
		for (; rutAux != 0; rutAux /= 10) {
		s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
		}
		if (dv == (char) (s != 0 ? s + 47 : 75)) {
		validation = true;
		}

		} catch (java.lang.NumberFormatException e) {
			} catch (Exception e) {
		}
		return validation;
		}
	
	
	@Autowired
	private StudentDAO studentDAO;

	
	/*
	 *  The following rest service responds all the students
	 *  in the table 'students' 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> students = studentDAO.findAll();
		return ResponseEntity.ok(students);	
	}	
	
	/*
	 *	The following rest service delivers the data from the student RUT delivered in the URL 
	 *	If not found, returns status 404
	 */
	
	@RequestMapping(value="{studentRut}",headers = "content-type=application/json")
	public ResponseEntity<Student> getStudentByRut(@PathVariable("studentRut") String studentRut){
		Optional<Student> optionalStudent = studentDAO.findById(studentRut);
		if (optionalStudent.isPresent()) {
			return ResponseEntity.ok(optionalStudent.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 *  the following rest service will generate a new record in the SQL table 'students' from the JSON data
	 *  Before adding it, it's verified that the student RUT is valid and has an age greater than 18 years
	 */
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		if ((validateRut(student.getRut()) == true) && (student.getAge() > 18)) {	
			Student newStudent = studentDAO.save(student);
			return ResponseEntity.ok(newStudent);
		}
		else return ResponseEntity.badRequest().build();
	}
	
	/*
	 *  The following rest service updates the student data
	 *  indicated in the URL with the data provided in the JSON.
	 *  It's also validated that the new age is greater than 18 years
	 */
	
	@PutMapping(value="{studentRut}", headers = "content-type=application/json")
	public ResponseEntity<Student> updateStudent(@PathVariable("studentRut") String studentRut,@RequestBody Student student){
		Optional<Student> optionalStudent = studentDAO.findById(student.getRut());
		if (optionalStudent.isPresent() && student.getAge()>18) {
			Student updateStudent = optionalStudent.get();
			updateStudent.setName(student.getName());
			updateStudent.setLastName(student.getLastName());
			updateStudent.setAge(student.getAge());
			updateStudent.setCourse(student.getCourse());
			studentDAO.save(updateStudent);
			return ResponseEntity.ok(updateStudent);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	/*
	 *  The following rest service removes the student indicated in the URL from the SQL table 'students'
	 *  If not found, returns status 404
	 */
	@DeleteMapping(value="{studentRut}", headers = "content-type=application/json")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentRut") String studentRut){
		Optional<Student> optionalStudent = studentDAO.findById(studentRut);
		if (optionalStudent.isPresent()) {
			studentDAO.deleteById(studentRut);
			return ResponseEntity.ok().build();	
		} else { 
			return ResponseEntity.notFound().build();
		}
	}
}
