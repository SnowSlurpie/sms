package jpa.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO {

	List<Student>getAllStudents(Session session);

	Student getStudentByEmail(String email, Session session);

	boolean validateStudent(Session session, String emailInput);

	void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList);
	
	Set<Course> getStudentCourses(Student student, Session session);
}