// Hibernate EHCache integration with cache features
// Along with HQL 

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AppEhCacheQuery 
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
        // Let's try an HQL query to use second level cache
        Query q1 = session1.createQuery("from AlienEhCache where aid = 1");
        
        // This is required to let hibernate know the HQL query is enabled to use second level caching
        // Otherwise even if the config (cfg.xml) has a property set to true for "hibernate.cache.use_query_cache"
        // it will still hit the database for query multiple times without using cache
        q1.setCacheable(true);   
        
        a = (AlienEhCache) q1.uniqueResult();   // uniqueResult() is used instead of get to fetch unique record thru HQL 
        // a = (AlienEhCache)session1.get(AlienEhCache.class, 1);    // get() only works with hibernate-ehcache but not with HQL   
        System.out.println(a);									  // for HQL we need to include one more property in cfg.xml file
        session1.getTransaction().commit(); 
        session1.close();

        // Session 2
        Session session2 =  sf.openSession();                      
        session2.beginTransaction();
        Query q2 = session2.createQuery("from AlienEhCache where aid = 1");
        
     // This is required to let hibernate know the HQL query is enabled to use second level caching
        // Otherwise even if the config (cfg.xml) has a property set to true for "hibernate.cache.use_query_cache"
        // it will still hit the database for query multiple times without using cache
        q2.setCacheable(true);
        
        a = (AlienEhCache) q2.uniqueResult();   // uniqueResult() is used instead of get to fetch unique record thru HQL
        // a = (AlienEhCache)session2.get(AlienEhCache.class, 1);   // this will fire only one query with the database      
        System.out.println(a);								 // As second level of Caching is enabled thru
        session2.getTransaction().commit(); 				 // pom.xml, hibernate.cfg.xml, AlienEhCache Entity
        session2.close();				
        
        /*
         * Note:
         * Executing the above code still does not enable one query to database resulting in 2 results
         * This is because there needs to be another property set in hibernate.cfg.xml file
         */
    }
}
