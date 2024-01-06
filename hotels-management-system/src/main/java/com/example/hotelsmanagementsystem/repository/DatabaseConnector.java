package com.example.hotelsmanagementsystem.repository;

import com.example.hotelsmanagementsystem.models.Department;
import com.example.hotelsmanagementsystem.models.Description;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector implements DatabaseInterface{
    private SessionFactory sessionFactory;

    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    protected void tearDown() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }


    public void saveDescriptionToDB(String description){
        Description desc = new Description(description);

        try{
            setUp();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.persist(desc);

            session.getTransaction().commit();

            tearDown();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Description> getDescriptions(){
        List<Description> descriptions;

        try {
            setUp();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            descriptions = session.createQuery("select d from Description d", Description.class).list();
//            descriptions.forEach(System.out::println);
            session.getTransaction().commit();

            tearDown();
            return descriptions;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<String> getAllDepartments() {

        return new ArrayList<>();
    }
}
