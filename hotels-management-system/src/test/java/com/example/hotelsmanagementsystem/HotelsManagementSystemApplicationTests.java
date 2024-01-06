package com.example.hotelsmanagementsystem;

import com.example.hotelsmanagementsystem.models.Description;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HotelsManagementSystemApplicationTests {

	private SessionFactory sessionFactory;

	@BeforeEach
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

	@AfterEach
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@Test
	protected void saveDescriptionToDB(){
		Description description = new Description("TEST");
		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();

			session.persist(description);

			session.getTransaction().commit();
		}
	}

	@Test
	protected void getDescriptions(){

		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();

			List<Description> descriptions = session.createQuery("select d from Description d", Description.class).list();
			descriptions.forEach(System.out::println);

			session.getTransaction().commit();
		}
	}

	@Test
	void contextLoads() {
	}

}
