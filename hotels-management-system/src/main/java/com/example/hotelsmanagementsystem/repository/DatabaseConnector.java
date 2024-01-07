package com.example.hotelsmanagementsystem.repository;

import com.example.hotelsmanagementsystem.models.Department;
import com.example.hotelsmanagementsystem.models.Description;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector{
    private SessionFactory sessionFactory;

    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!                      //fixme: wykombinowac jak to zrobic
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


    public void saveDescriptionToDB(Description description){
        //Description desc = new Description(description);

        try{
            setUp();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.persist(description);

            session.getTransaction().commit();

            tearDown();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Description> getDescriptions(){
        List descriptions;

        try {
            setUp();

            Session session = sessionFactory.openSession();
            //session.beginTransaction();

            //NativeQuery<Description> s = new NativeQuery<Description>();

            descriptions = session.createNativeQuery("CALL GetDescriptions()").addEntity(Description.class).list();
            //.addEntity(Description.class).stream().toList();

            //descriptions = session.createQuery("select d from Description d", Description.class).list();
//            descriptions.forEach(System.out::println);
            //session.getTransaction().commit();

            tearDown();
            return descriptions;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> getDepartmentsNames(){
        List names;

        try {
            setUp();

            Session session = sessionFactory.openSession();         //fixme: zwraca ze session == null
            names = session.createNativeQuery("CALL GetDepartmentNames()").addEntity(String.class).list();

            tearDown();
            return names;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Department> getDepartmentsInfo(){
        List departments;

        try {
            if (sessionFactory == null){
                setUp();
            }

            //setUp();

            Session session = sessionFactory.openSession();                 //fixme: zwraca ze session == null
            departments = session.createNativeQuery("CALL GetDepartments()").addEntity(Department.class).list();

            tearDown();
            return departments;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
