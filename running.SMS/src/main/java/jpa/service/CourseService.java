package jpa.service;

import java.util.Iterator;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import sms.util.HibernateUtil;

public class CourseService extends HibernateUtil implements CourseDAO {

	@Override
	public 	List<Course> getAllCourses(Session session) {
		String hql = "FROM Course";
		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		List<Course> results = query.getResultList();
		Iterator<Course> cList = results.iterator();
		while(cList.hasNext()) {
			Course c =cList.next();
			System.out.println("Course id: " + c.getcId() + ", Name: " + c.getcName() + ", Instructor: " + c.getcInstructorName());
		}
		return results;
	}

}