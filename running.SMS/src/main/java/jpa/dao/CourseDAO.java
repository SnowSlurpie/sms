package jpa.dao;

import java.util.List;

import org.hibernate.Session;

import jpa.entitymodels.Course;

public interface CourseDAO {
	List<Course>getAllCourses(Session session);
}
