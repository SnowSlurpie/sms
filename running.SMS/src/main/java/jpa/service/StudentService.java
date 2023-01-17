package jpa.service;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import sms.util.HibernateUtil;


//This class creates the information and code that is utilized in the StudentDAO

public class StudentService extends HibernateUtil implements StudentDAO {

	@Override
	public List<Student> getAllStudents(Session session) {

		String hql = "FROM Student";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		
		List<Student> results = new ArrayList<>();
		try {
			results = query.getResultList();
			Iterator<Student> itr = results.iterator();
			while(itr.hasNext()) {
				Student s = itr.next();
				System.out.println("Name: " + s.getsName() + ", Email : "+ s.getsEmail() + ", Password" + s.getsPass() + " Course: " + s.getsCourses());
			}
				
		} catch (NoResultException e ) {
			System.out.println("No rows found in students table. You found a secret!");
			return results;	
		}
		return results;
	}

	@Override
	public Student getStudentByEmail(String email, Session session) {
		
		String hql = "FROM Student WHERE email = :email ";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", email);
		Student s = (Student) query.getSingleResult();
		
		System.out.println("Name: " + s.getsName() + ", Email : "+ s.getsEmail() + ", Password" + s.getsPass()+ " Course: " + s.getsCourses());
		return s;
	}

	

	@Override
	public Set<Course> getStudentCourses(Student student, Session session) {

		Set<Course> cList = student.getsCourses();
		Iterator<Course> itr = cList.iterator();
		
		while(itr.hasNext()) {
			Course c = itr.next();
			System.out.println(c.getcId() + "  " + c.getcName() + "  " + c.getcInstructorName());
		}
		return cList;
	}

	@Override
	public void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList) {
		
		//create a courseService Object
		CourseDAO crsService = new CourseService();
		
		//Get selected courses
		Course c1 = crsList.get(courseId);
		
		//get current students, current courses
		Set<Course> currentList = student.getsCourses();
		
		//Ads course to array list, saves and stores it.
		currentList.add(c1);
		session.save(c1);
		student.setsCourses(currentList);
		session.save(student);
	}

@Override
public boolean validateStudent(Session session, String emailInput) {

	//User input line
	Scanner input = new Scanner(System.in); 
	System.out.println("Validating Credenitals");
	String hql = "FROM Student WHERE email = :email ";
	
	TypedQuery<Student> query = session.createQuery(hql, Student.class);
	query.setParameter("email", emailInput);
	
	//Results
	try {
		Student std = (Student)query.getSingleResult();
		System.out.println("\n>>Email confirmed<<");
		
		//User input
		System.out.println("Please enter password..");
		String passwordInput = input.nextLine();
		
		//Password for current student
		String expectedPassword = std.getsPass();
		
		//makes sure the password is correct
		if(expectedPassword.equals(passwordInput)) {
			System.out.println("Validation successful");
			return true;
			
		}else {
			System.out.println("Password is incorrect");
			return false;
		}
		
	} catch (NoResultException e) {
		e.printStackTrace();
		System.out.println("No result! You found a secret!");
		return false;
	}
}
}
