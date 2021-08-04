// Hibernate with SQL query instead of HQL
// Native Queries

package com.pcsglobal.HibernateDemo;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AppSql 
{
    public static void main( String[] args )
    {
           
        Configuration con = new Configuration().configure().addAnnotatedClass(Students.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session =  sf.openSession(); 
        
        session.beginTransaction();  
        
      /*
        // Native Queries:- SQL query with all fields of the table as an Entity object
        SQLQuery query = session.createSQLQuery("select * from students_hql where marks > 60" );
        // SQL refers to a table instead of an Object (Entity Class). So it returns hash code
        query.addEntity(Students.class);  // Here it helps to have a List of Students object from the query
        List<Students> students = (List<Students>) query.list();  
        
        for (Object o: students)
        {
        	System.out.println(o);
        }
      */
        
        // Native Queries:- SQL query with selected/limited fields of the table not as an Entity object
        SQLQuery q = session.createSQLQuery("select name, marks from students_hql where marks > 60" );
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);  // transforms the query result into a MAP
        List stud =  q.list();  
        
        for (Object o: stud)
        {
        	Map m = (Map)o;  // convert the object to Map (key:value pair)
        	System.out.println(m.get("name") + " : " + m.get("marks"));
        }
        
        session.getTransaction().commit(); 				 
        session.close();				
        
    }
}
