// Fetching data from database using Hibernate

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AppFetch 
{
    public static void main( String[] args )
    {
        Alien pcs = new Alien();
         
        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class); 
        // Finally Configuration is a class from which an object of a session through SessionFactory can be created
        // but still con object is not connected to hibernate.cfg.xml and therefore the configure method is used
        // if not an error "cannot be null when 'hibernate.dialect'" is encountered
        // Now, this gives rise to another error when run "Unknown entity: com.pcsglobal.HibernateDemo.Alien"
        // Even after adding annotation @Entity in the Bean class Alien, the same error happens, 
        // so need to add AnnotatedClass as configure method
        
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        
        /* SessionFactory sf = con.buildSessionFactory();	*/			
        // SessionFactory is an interface therefore an object cannot be created. 
        // A configuration Object method creates sf object
        
        SessionFactory sf = con.buildSessionFactory(reg);
        // the deprecated buildSessionFactory can be corrected by putting an object of ServiceRegistry as argument
        
        Session session =  sf.openSession();                      
        // Session is an interface therefore an object cannot be created. 
        // A session factory object sf having method openSession will create the session object
              
        Transaction tx = session.beginTransaction();
        
        pcs = (Alien) session.get(Alien.class, 3);  // Fetch the data from database using session.get object
        
        tx.commit(); // commiting the transaction in database
        
        System.out.println(pcs);
    }
}
