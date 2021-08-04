// Hibernate with SQL query instead of HQL
// Native Queries

package com.pcsglobal.HibernateDemo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AppLifeCycle 
{
    public static void main( String[] args )
    {
           
        Configuration con = new Configuration().configure().addAnnotatedClass(LaptopLifeCycle.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session =  sf.openSession(); 
        
        session.beginTransaction();  
        
        LaptopLifeCycle lap = new LaptopLifeCycle();  // laptop_cycle object created
        // "Transient" state that is temporary state
        lap.setLid(104);
        lap.setBrand("Lenovo3");
        lap.setPrice(350);
        
        // "Persistent" state that is stored in database
        session.save(lap);  // It is still not persisted unless committed
        lap.setPrice(575);  
        // In the meantime the if the price is modified then the modified price is persisted when commit
        // in the console u will see Hibernate first Inserts then updates (remember to make cfg file "show_sql" to true)
        
        // "Remove() state
        session.delete(lap);  // it is like session.delete(from LaptopLifeCycle where lid = 104);
        
        session.getTransaction().commit(); 
        
        // Now, to keep the earlier price in the database and 
        // make the customer some offering in price which will not be reflected in database, we need to 
        // "detach()" the price offering from the database persistence
        session.evict(lap);
        lap.setPrice(400);
        
        session.close();				
        
    }
}
