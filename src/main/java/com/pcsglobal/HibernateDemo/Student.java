// Student Entity class

package com.pcsglobal.HibernateDemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student 
{
	@Id
	private int rollno;
	private String name;
	private int marks;
	
	// The annotation is needed to avoid an error as Hibernate cannot understand what is the relation of Student(object) with Laptop(object) 
	// Error: Could not determine type for: com.pcsglobal.HibernateDemo.Laptop, at table: Student, for columns: [org.hibernate.mapping.Column(laptop)]
	/* @OneToOne    
	   private Laptop laptop; */
	// Every student should have at least one Laptop
	// @OnetoOne output of student is :- rollno = 1, marks=89, name='Debasis', laptop_lid=101
	
	/* @OneToMany(mappedBy = "student") // student is the object of Student created in Laptop as attribute
	private List<Laptop> laptop = new ArrayList<Laptop>(); */
	// One student can have more than one Laptop
	// @OneToMany output is a mapping table "student_laptop" :- rollno = 1, laptop_lid=101 3 Indexes, 2 FKs
	// @OneToMany output of student is:- rollno = 1, marks=89, name='Debasis'
	// @OneToMany output of laptop is:- lid = 101, lname= Lenovo

	@ManyToMany(mappedBy = "student") // student is the object of Student created in Laptop as attribute
	private List<Laptop> laptop = new ArrayList<Laptop>();
	// Many student can have more than one Laptop
	// @ManyToMany output is a mapping table "laptop_student" :- rollno = 1, laptop_lid=101 3 Indexes, 2 FKs
	// @ManyToMany output of student is:- rollno = 1, marks=89, name='Debasis'
	// @ManyToMany output of laptop is:- lid = 101, lname= Lenovo
	
	public List<Laptop> getLaptop() {
		return laptop;
	}

	public void setLaptop(List<Laptop> laptop) {
		this.laptop = laptop;
	}
	
	public int getRollno() {
		return rollno;
	}
	
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", marks=" + marks + "]";
	}
}
