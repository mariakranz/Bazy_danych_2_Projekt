package com.example.hotelsmanagementsystem;

import com.example.hotelsmanagementsystem.models.*;
import com.example.hotelsmanagementsystem.service.LoginService;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

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
			//session.beginTransaction();

			List<Description> descriptions = session.createQuery("select d from Description d", Description.class).list();
			descriptions.forEach(System.out::println);

			//session.getTransaction().commit();
		}
	}

	@Test
	protected void getDepartmentsInfo() {
		try (Session session = sessionFactory.openSession()) {
			StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetDepartments", Department.class);

			List<Department> list = storedProcedure.getResultList();

			list.forEach(System.out::println);
			//return storedProcedure.getResultList();

		} catch (Exception e) {
			throw new RuntimeException("Error getting department info", e);
		}
	}

	@Test
	protected void getEmployeeInfoById(){
		int id = 2;
		try (Session session = sessionFactory.openSession()) {
			StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetEmployeeInfoByID");
			storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_EmployeeName", String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter("p_EmployeeSurname", String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter("p_EmployeePhone", String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter("p_EmployeeEmail", String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter("p_EmployeePrivilege", Integer.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter("p_DepartmentName", String.class, ParameterMode.OUT);

			storedProcedure.setParameter("p_EmployeeID", id);

			storedProcedure.execute();

			EmployeeInfo employee = new EmployeeInfo( id,
					(String) storedProcedure.getOutputParameterValue("p_EmployeeName"),
					(String) storedProcedure.getOutputParameterValue("p_EmployeeSurname"),
					(String) storedProcedure.getOutputParameterValue("p_EmployeePhone"),
					(String) storedProcedure.getOutputParameterValue("p_EmployeeEmail"),
					(int) storedProcedure.getOutputParameterValue("p_EmployeePrivilege"),
					(String) storedProcedure.getOutputParameterValue("p_DepartmentName"));

			System.out.println(employee.toString());

		} catch (Exception e) {
			throw new RuntimeException("Error finding employee.", e);
		}
	}

	@Test
	protected void createNewBooking( ) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date dateInThreeDays = calendar.getTime();


//		Booking newBooking = new Booking("TestName", "TestSurname", "1112-2020-20", "TestName.TestSurname@mail.com", date, dateInThreeDays, new Room());
		String clientName ="TestName";
		String clientSurname = "TestSurname";
		String phoneNumber = "123456789";
		String email = "TestName.TestSurname@mail.com";
		Date startDate = date;
		Date endDate = dateInThreeDays;
		int roomID = 1;

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("InsertBooking");
			storedProcedure.registerStoredProcedureParameter("p_ClientName", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_ClientSurname", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_PhoneNumber", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_Email", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_StartDate", Date.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_EndDate", Date.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_RoomID", Integer.class, ParameterMode.IN);

			storedProcedure.setParameter("p_ClientName", clientName);
			storedProcedure.setParameter("p_ClientSurname", clientSurname);
			storedProcedure.setParameter("p_PhoneNumber", phoneNumber);
			storedProcedure.setParameter("p_Email", email);
			storedProcedure.setParameter("p_StartDate", startDate);
			storedProcedure.setParameter("p_EndDate", endDate);
			storedProcedure.setParameter("p_RoomID", roomID);

			storedProcedure.execute();
			session.getTransaction().commit();

		} catch (Exception e) {
			throw new RuntimeException("Error creating booking.", e);
		}
	}

//	@Test
//	protected void getRoomInfoById(){
//		int id = 1;
//
//		try (Session session = sessionFactory.openSession()) {
//			StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetRoomInfoByID");
//			storedProcedure.registerStoredProcedureParameter("p_RoomID", Integer.class, ParameterMode.IN);
//			storedProcedure.registerStoredProcedureParameter("p_Number", Integer.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_Type", String.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_BedsNumber", Integer.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_RoomDescription", String.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_City", String.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_Street", String.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_BuildingDescription", String.class, ParameterMode.OUT);
//			storedProcedure.registerStoredProcedureParameter("p_RoomCount", Long.class, ParameterMode.OUT);
//
//			storedProcedure.setParameter("p_RoomID", id);
//
//			storedProcedure.execute();
//
//			RoomInfo room = new RoomInfo( (int) storedProcedure.getOutputParameterValue("p_Number"),
//					(String) storedProcedure.getOutputParameterValue("p_Type"),
//					(int) storedProcedure.getOutputParameterValue("p_BedsNumber"),
//					(String) storedProcedure.getOutputParameterValue("p_RoomDescription"),
//					(String) storedProcedure.getOutputParameterValue("p_City"),
//					(String) storedProcedure.getOutputParameterValue("p_Street"),
//					(String) storedProcedure.getOutputParameterValue("p_BuildingDescription"),
//					(Long) storedProcedure.getOutputParameterValue("p_RoomCount"));
//
//			System.out.println(room);
//		} catch (Exception e) {
//			throw new RuntimeException("Error finding employee.", e);
//		}
//	}
}
