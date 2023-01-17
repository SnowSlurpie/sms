package jpa.service;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import jpa.entitymodels.Student;
import jpa.entitymodels.Course;


public class MenuService {
	
	public CourseService cService = new CourseService();
	public StudentService sService =  new StudentService();
	
	public void menu(Session session) {
		
		int userInput = 0;
		boolean runningMenu = true;
		Scanner console = new Scanner(System.in);
		
		while(runningMenu) {
			System.out.println("\n***** Main Menu *****\n");
			System.out.println("1. Login");
			System.out.println("2. Exit\n");
			System.out.println("Please, enter 1 or 2.");
			userInput = console.nextInt();
			
			switch(userInput) {
			case 1:
				System.out.println("Logging in user..");
				boolean userLoggedIn = false;
				
				//User prompt to login.
				System.out.println("Enter your email:");
				Scanner input = new Scanner(System.in);
				String emailInput = input.nextLine();
				
				//This below block handles the Login functionality.
				if(login(session, sService, emailInput)) {
					userLoggedIn = true;
					///Fetches the current student.
					Student student = sService.getStudentByEmail(emailInput, session);

					System.out.println("\n***** Welcome"+ student.getsName()+ " ========\n");
					System.out.println("My Enrolled Classes:\n");
					System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
					sService.getStudentCourses(student, session);
					
					System.out.println("\n***Select An Option***\n");
					System.out.println("1. Register to Class");
					System.out.println("2. Logout");
					
					int courseSelection = input.nextInt();
					
					switch (courseSelection) {
					case 1:
						//Shows all possible Courses someone can enroll in.
						System.out.println("\nAll Courses:\n");
						System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
						List <Course> clist = cService.getAllCourses(session);
						System.out.println("\nPlease select a course");
						
						int courseId = console.nextInt();
						sService.registerStudentToCourse(student, session, courseId, clist);
//						This will show all of the courses that a Student is enrolled in.
						System.out.println("My Classes:\n");
						System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
						sService.getStudentCourses(student, session);
						
						break;
					case 2:
						runningMenu =logout();
						break;
						
					default:
						break;
					}
				}
				break;
			
			case 2:
				runningMenu = logout();
				break;
			
			default:
				System.out.println("Invalid input");
				break;
			}
		}
	}
	public boolean login(Session session, StudentService service, String emailInput){
		
		//This line handles email(aka login) validation 
		boolean isvalid = sService.validateStudent(session, emailInput);
		
		if(isvalid) {
			System.out.println("Login successful.");
			return true;
		}else {
			System.out.println("Login failed.");
			return false;
		}
	}
//	Successful logout line
	public boolean logout() {
		System.out.println("You have been signed out.");
		return false;
	}
}