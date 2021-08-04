// Hibernate EHCache integration with cache features
// Along with HQL Part 1 & 2

package com.pcsglobal.HibernateDemo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AppHql 
{
    public static void main( String[] args )
    {
           
        Configuration con = new Configuration().configure().addAnnotatedClass(Students.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session =  sf.openSession(); 
        
        session.beginTransaction();  
        
      // HQL Part 1   
      /* After the table has been inserted (save) with 50 rows we will query the table with HQL 
        Random r = new Random();
        // Insert into students_hql by hibernate
        for (int i=1; i<=50; i++)
        {
        	Students s = new Students();
        	s.setRollno(i);
        	s.setName("Name"+i);
        	s.setMarks(r.nextInt(100));  // marks cannot exceed 100
        	session.save(s);
        }
        // The cfg file has changed from "create" to "update" so as not to drop the table
      */
        
        // Query q = session.createQuery("from Students");  // HQL 1
        // Query q = session.createQuery("from Students where marks > 50");  // HQL 2

      /* Print the List
        List<Students> students = q.list(); // This is the method for getting the resultset in hibernate 
        
        for (Students s: students)
        {
        	System.out.println(s);
        }
      */
        
      /*
        // Query with Select 
        { 
        	Query q = session.createQuery("select count(*) from Students where marks >= 50");  // HQL 3
        	System.out.println("No. of Students who got >=50 ::>> " + q.uniqueResult());
        	
        	Query q2 = session.createQuery("select count(*) from Students where marks < 50");  // HQL 4
        	System.out.println("No. of Students who got <50  ::>> " + q2.uniqueResult());
        	
        	Query q3 = session.createQuery("select count(*)/50*100 from Students where marks >= 50");  // HQL 5
        	System.out.println("%age of Students who got >=50 ::>> " + q3.uniqueResult() + "%");
        	
        	Query q4 = session.createQuery("select count(*)/50*100 from Students where marks < 50");  // HQL 6
        	System.out.println("%age of Students who got <50  ::>> " + q4.uniqueResult() + "%");
        }
      */
              
      // HQL Part 2 (Query using columns in tables)
      /* 1 row
        Query q5 = session.createQuery("select rollno, name, marks from Students where rollno = 7");  // HQL 7
        Object[] student = (Object[])q5.uniqueResult();
        System.out.println("Rollno: "+student[0]+ "  Name: "+student[1]+ "  Marks: "+student[2]);
      */
        
      /* More than 1 rows
        Query q6 = session.createQuery("select rollno, name, marks from Students");  // HQL 8
        List<Object[]> student1 = (List<Object[]>) q6.list();
        for (Object[] o: student1)
        {
        	System.out.println("Rollno: "+o[0]+ "  Name: "+o[1]+ "  Marks: "+o[2]);
        }
      */
        
        // fetch 1 column 
        Query q7 = session.createQuery("select sum(marks) from Students s where s.marks > 60");  // HQL 9
        Long marks = (Long)q7.uniqueResult();
        System.out.println("Total Marks: " + marks);
        
        // fetch 1 column with much more simplified preparedStatement (JDBC) style 
        int m = 60;
        Query q8 = session.createQuery("select sum(marks) from Students s where s.marks > :m ");  // HQL 10
        q8.setParameter("m", m);
        Long marks1 = (Long)q8.uniqueResult();
        System.out.println("Total Marks: " + marks1);
        
        session.getTransaction().commit(); 				 
        session.close();				
        
    }
}
