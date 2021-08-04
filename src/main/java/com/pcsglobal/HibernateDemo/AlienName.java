// Embedding alien_name column with fname, mname, lname

package com.pcsglobal.HibernateDemo;

import javax.persistence.Embeddable;

// This annotation is used to indicate to Hibernate to 
// embed these 3 fields in the same table "alien_test" implemented by AlienEmbed Entity class
@Embeddable    
public class AlienName // Bean class or POJO
{   
	private String fname;
	private String mname;
	private String lname;

	@Override
	public String toString() {
		return "AlienName [fname=" + fname + ", mname=" + mname + ", lname=" + lname + "]";
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
	
}
