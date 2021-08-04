// Embedding & Saving data from/to database using Hibernate

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AppEmbedSave 
{
    public static void main( String[] args )
    {
        AlienEmbed pcs = new AlienEmbed();   	// The main Alien Entity class 
        
        AlienName an = new AlienName();			// The Alien Name class for the 3 name attributes to embed       
        an.setFname("Debasis");
        an.setMname("Kumar");
        an.setLname("Paul");
        
        pcs.setAid(5);
        pcs.setAname(an);
        pcs.setTech("Big Data");
        pcs.setColor("Yellow");
         
        Configuration con = new Configuration().configure().addAnnotatedClass(AlienEmbed.class); 
        
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        		
        SessionFactory sf = con.buildSessionFactory(reg);
         
        Session session =  sf.openSession();                      
                
        Transaction tx = session.beginTransaction();
        
        session.save(pcs);
        
        tx.commit(); 
        
        System.out.println(pcs);
    }
}
