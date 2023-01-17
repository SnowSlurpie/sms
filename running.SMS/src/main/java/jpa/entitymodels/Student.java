package jpa.entitymodels;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "email", length = 50)
	private String sEmail;
	
	@Column(name = "name")
	private String sName;
	
	@Column(name = "password")
	private String sPass;
	 
	@ManyToMany(targetEntity=Course.class, cascade= {(CascadeType.ALL)})
	private Set<Course> sCourses;
	
	public Student() {
		
		this.sEmail = null;
		this.sName = null;
		this.sPass = null; 
	}
	public Student(String sEmail, String sName, String sPass, Set<Course> sCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public Set<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(Set<Course> sCourses) {
		this.sCourses = sCourses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses=" + sCourses + "]";
	}
	
}