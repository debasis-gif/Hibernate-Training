// Alien Embed Entity which embeds AlienName class for fname, mname, lname into the existing structure

package com.pcsglobal.HibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;  // JPA
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;

@Entity  
@Table(name = "alien_test") 
public class AlienEmbed // Bean class or POJO
{   
	@Id
	private int aid;
	private AlienName aname;  // Here aname is the object of the class AlienName with name split attributes
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

	public AlienName getAname() {
		return aname;
	}

	public void setAname(AlienName aname) {
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
