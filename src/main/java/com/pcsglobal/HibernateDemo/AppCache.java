// Hibernate EHCache integration with cache features
// Fetch features
// First Level Cache

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AppCache 
{
    public static void main( String[] args )
    {
        AlienCache a = null;
        
        Configuration con = new Configuration().configure().addAnnotatedClass(AlienCache.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);

        // Session 1
        Session session1 =  sf.openSession();                      
        session1.beginTransaction();     
        a = (AlienCache)session1.get(AlienCache.class, 1);       
        System.out.println(a);
        session1.getTransaction().commit(); 
        session1.close();

        // a = (AlienCache)session1.get(AlienCache.class, 2);   	// this will fire another query with the database  
        // a = (AlienCache)session1.get(AlienCache.class, 1);     	// Now, this will fire 1 query for 2 results 
        // System.out.println(a);									// as it fetches the data from first level cache  
          
        // Session 2
        Session session2 =  sf.openSession();                      
        session2.beginTransaction();
        a = (AlienCache)session2.get(AlienCache.class, 1);   // this will fire another query with the database      
        System.out.println(a);								 // as it cannot fetch anything from first level cache of session2
        session2.getTransaction().commit(); 				 // this still happens to hit the database even after pom.xml 
        session2.close();									 // and hibernate.cfg.xml is configured for second level cache
        													 // for second level cache additionally Entity class has to use
        													 // annotations of Cachable & Cache -> AlienEhCache, AppEhCache
    }
}
