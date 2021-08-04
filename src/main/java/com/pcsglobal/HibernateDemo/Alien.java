// Entity bean

package com.pcsglobal.HibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;  // JPA
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;


// Annotation is added after the error encountered "Unknown entity: com.pcsglobal.HibernateDemo.Alien"
@Entity  
// Annotation will create/update (property -> hbm2ddl.auto) table with alien_test instead of Entity Alien 
@Table(name = "alien_test") 
public class Alien // Bean class or POJO
{   
	// missing the Primary Key annotation will give rise to Error:
	// "No identifier specified for entity: com.pcsglobal.HibernateDemo.Alien"
	@Id
	private int aid;
	// If the column name in the table needs to be different from the attribute "aname"
	@Column(name = "alien_name") 
	private String aname;
	// If the column needs to be hidden from the table
	//@Transient
	private String tech;
	private String color;

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", tech=" + tech + ", color=" + color + "]";
	}
	
	
}
