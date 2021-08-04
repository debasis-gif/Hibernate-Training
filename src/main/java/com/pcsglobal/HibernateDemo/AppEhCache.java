// Hibernate EHCache integration with cache features
// Fetch features
// Second Level Cache configuration

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppEhCache 
{
    public static void main( String[] args )
    {
        AlienEhCache a = null;
        
        Configuration con = new Configuration().configure().addAnnotatedClass(AlienEhCache.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);

        // Session 1
        Session session1 =  sf.openSession();                      
        session1.beginTransaction();     
        a = (AlienEhCache)session1.get(AlienEhCache.class, 1);       
        System.out.println(a);
        session1.getTransaction().commit(); 
        session1.close();

        // Session 2
        Session session2 =  sf.openSession();                      
        session2.beginTransaction();
        a = (AlienEhCache)session2.get(AlienEhCache.class, 1);   // this will fire only one query with the database      
        System.out.println(a);								 // As second level of Caching is enabled thru
        session2.getTransaction().commit(); 				 // pom.xml, hibernate.cfg.xml, AlienEhCache Entity
        session2.close();									 
    }
}
