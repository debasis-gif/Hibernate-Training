// Hibernate implementing RDBMS Relations 

package com.pcsglobal.HibernateDemo;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AppStudentLaptop 
{
    public static void main( String[] args )
    {
        Laptop laptop = new Laptop();
        laptop.setLid(101);
        laptop.setLname("Lenovo");
        
        Student stud = new Student();
        stud.setRollno(1);
        stud.setName("Debasis");
        stud.setMarks(89);
        
        // After @OneToMany in Student and @ManyToOne in Laptop we need to add List of Laptops into ArrayList of Laptop 
        // where one student can have relation to multiple laptops 
        stud.getLaptop().add(laptop);  
        
        // After @ManyToMany in both Laptop & Student we need to add Student into Laptop as well
        laptop.getStudent().add(stud);
        
        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class); 
        
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
               
        SessionFactory sf = con.buildSessionFactory(reg);
        
        Session session =  sf.openSession();                      
        
        Transaction tx = session.beginTransaction();
        
        session.save(stud);
        session.save(laptop);
        
        
        tx.commit(); // OR
        //session.getTransaction().commit();
    }
}
