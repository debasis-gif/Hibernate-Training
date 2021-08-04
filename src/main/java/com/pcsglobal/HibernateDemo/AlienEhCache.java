// Alien Entity bean for Cache demo
// Second Level Cache implementation along with EhCache configuration in pom.xml & hibernate.cfg.xml

package com.pcsglobal.HibernateDemo;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity  
@Table(name = "alien")
// To implement second level caching strategy in Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class AlienEhCache 
{   
	@Id
	private int aid;
	private String aname;
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
		return "AlienCache [aid=" + aid + ", aname=" + aname + ", tech=" + tech + ", color=" + color + "]";
	}	
}
