// Laptop Entity class

package com.pcsglobal.HibernateDemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Laptop 
{
	@Id
	private int lid;
	private String lname;
	
	/*  @ManyToOne  
		private Student student; 
	*/	
	// Many Laptops with one student
	// @ManyToOne output is a mapping table "student_laptop" :- rollno = 1, laptop_lid=101 3 Indexes, 2 FKs
	// @ManyToOne output of student is:- rollno = 1, marks=89, name='Debasis'
	// @ManyToOne output of laptop is:- lid = 101, lname= Lenovo, student_rollno=null
	// To avoid getting the extra mapping table student_laptop, we need to annotate Student table to specify laptop to do the mapping
	// after we specify the Student table with the annotation the output is:-
	// @ManyToOne output of student is:- rollno = 1, marks=89, name='Debasis'
	// @ManyToOne output of laptop is:- lid = 101, lname= Lenovo, student_rollno=null still we have null
	// To avoid null in student_rollno of table laptop we do the @ManyToMany that is Many laptops with Many students <List>
	
	@ManyToMany  
	private List<Student> student = new ArrayList<Student>();
	// Many Laptops with many students
	// @ManyToOne output is a mapping table "student_laptop" :- rollno = 1, laptop_lid=101 3 Indexes, 2 FKs
	// @ManyToOne output of student is:- rollno = 1, marks=89, name='Debasis'
	// @ManyToOne output of laptop is:- lid = 101, lname= Lenovo, student_rollno=null
	
	public int getLid() {
		return lid;
	}

/*	When Student is one (ManyToOne)
 	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
*/	
	
/*  When Student is Many (ManyToMany) */
	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "Laptop [lid=" + lid + ", lname=" + lname + "]";
	}
}
