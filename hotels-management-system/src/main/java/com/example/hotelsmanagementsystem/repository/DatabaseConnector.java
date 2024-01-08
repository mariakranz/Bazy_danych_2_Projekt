package com.example.hotelsmanagementsystem.repository;

import com.example.hotelsmanagementsystem.models.*;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseConnector{
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Initialize the SessionFactory once
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Hibernate SessionFactory", e);
        }
    }

    public List<Description> getDescriptions() {        //fixme: zmienic na parametry out i reczne mapowanie
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetDescriptions", Description.class);
            return (List<Description>) storedProcedure.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error getting descriptions", e);
        }
    }

    public List<String> getDepartmentsNames() { //fixme: zmienic na parametry out i reczne mapowanie // UPDATE chyba nie da sie :')))
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetDepartmentNames");
            return storedProcedure.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error getting department names", e);
        }
    }

    public List<Department> getDepartmentsInfo(){   //fixme: zmienic na parametry out i reczne mapowanie
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetDepartments", Department.class);
            List<Department> departments = storedProcedure.getResultList();

            if (departments.isEmpty()) {
                System.out.println("No departments found.");
            } else {
                for (Department department : departments) {
                    System.out.println(department);
                }
            }

            return departments;
        } catch (Exception e) {
            System.out.println("Error getting department info: " + e.getMessage());
            throw new RuntimeException("Error getting department info", e);
        }
    }

    public int authenticateUser(String login, String password) {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("AuthenticateUser");
            storedProcedure.registerStoredProcedureParameter("p_Login", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_Password", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.OUT);

            storedProcedure.setParameter("p_Login", login);
            storedProcedure.setParameter("p_Password", password);

            storedProcedure.execute();

            return (int) storedProcedure.getOutputParameterValue("p_EmployeeID");
        } catch (Exception e) {
            return -1;
        }
    }
    public void updateLastLoginDate(int EmpId) {
            try (Session session = sessionFactory.openSession()) {
                StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateLastLoginDate");
                storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);

                storedProcedure.setParameter("p_EmployeeID", EmpId);

                storedProcedure.execute();

            } catch (Exception e) {
                throw new RuntimeException("Error authenticating user", e);
            }
        }

    public EmployeeInfo getEmployeeInfoByID(int id) {
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

            return new EmployeeInfo( (String) storedProcedure.getOutputParameterValue("p_EmployeeName"),
                    (String) storedProcedure.getOutputParameterValue("p_EmployeeSurname"),
                    (String) storedProcedure.getOutputParameterValue("p_EmployeePhone"),
                    (String) storedProcedure.getOutputParameterValue("p_EmployeeEmail"),
                    (int) storedProcedure.getOutputParameterValue("p_EmployeePrivilege"),
                    (String) storedProcedure.getOutputParameterValue("p_DepartmentName"));
        } catch (Exception e) {
            throw new RuntimeException("Error finding employee.", e);
        }
    }

//    public RoomInfo getRoomInfoByID(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetRoomInfoByID");
//            storedProcedure.registerStoredProcedureParameter("p_RoomID", Integer.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter("p_Number", Integer.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_Type", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_BedsNumber", Integer.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_RoomDescription", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_City", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_Street", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_BuildingDescription", String.class, ParameterMode.OUT);
//            storedProcedure.registerStoredProcedureParameter("p_RoomCount", Long.class, ParameterMode.OUT);
//
//            storedProcedure.setParameter("p_RoomID", id);
//
//            storedProcedure.execute();
//
//            return new RoomInfo( (int) storedProcedure.getOutputParameterValue("p_Number"),
//                    (String) storedProcedure.getOutputParameterValue("p_Type"),
//                    (int) storedProcedure.getOutputParameterValue("p_BedsNumber"),
//                    (String) storedProcedure.getOutputParameterValue("p_RoomDescription"),
//                    (String) storedProcedure.getOutputParameterValue("p_City"),
//                    (String) storedProcedure.getOutputParameterValue("p_Street"),
//                    (String) storedProcedure.getOutputParameterValue("p_BuildingDescription"),
//                    (Long) storedProcedure.getOutputParameterValue("p_RoomCount"));
//        } catch (Exception e) {
//            throw new RuntimeException("Error finding employee.", e);
//        }
//    }
    public List<RoomInfo> getRoomsInfo() {
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetRoomsInfo");
            storedProcedure.execute();
            List<RoomInfo> roomInfoList = new ArrayList<>();

            // Assuming your stored procedure returns columns in the same order as RoomInfo fields
            List<Object[]> resultList = storedProcedure.getResultList();
            for (Object[] result : resultList) {
                RoomInfo roomInfo = new RoomInfo(
                        (Integer) result[0],
                        (Integer) result[1],
                        (String) result[2],
                        (Integer) result[3],
                        (String) result[4],
                        (String) result[5],
                        (String) result[6],
                        (String) result[7],
                        (Long) result[8]);


                roomInfoList.add(roomInfo);
            }

            return roomInfoList;
        } catch (Exception e) {
            throw new RuntimeException("Error getting rooms info.", e);
        }
    }


    public void createNewBooking(String clientName, String clientSurname, String phoneNumber,
                                 String email, Date startDate, Date endDate, int roomID) {
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

//            storedProcedure.setParameter("p_ClientName", newBooking.getClientName());
//            storedProcedure.setParameter("p_ClientSurname", newBooking.getClientSurname());
//            storedProcedure.setParameter("p_PhoneNumber", newBooking.getPhoneNumber());
//            storedProcedure.setParameter("p_Email", newBooking.getEmail());
//            storedProcedure.setParameter("p_StartDate", newBooking.getStartDate());
//            storedProcedure.setParameter("p_EndDate", newBooking.getEndDate());
//            storedProcedure.setParameter("p_RoomID", newBooking.getRoom().getId());

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

    public void saveDescriptionToDB(String description) {
    }




//    private SessionFactory sessionFactory;
//
//    protected void setUp() throws Exception {
//        // A SessionFactory is set up once for an application!                      //fixme: wykombinowac jak to zrobic
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//            // so destroy it manually.
//            StandardServiceRegistryBuilder.destroy( registry );
//        }
//    }
//
//    protected void tearDown() throws Exception {
//        if ( sessionFactory != null ) {
//            sessionFactory.close();
//        }
//    }
//
//
//    @Transactional
//    public void saveDescriptionToDB(String description){
//        //Description newDesc = new Description("TEST");
//        try{
////            if (sessionFactory == null) {
////                setUp();
////            }
//
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("InsertDescription");
//
//            storedProcedure.registerStoredProcedureParameter("p_DescriptionText", String.class, ParameterMode.IN);
//
//
//            storedProcedure.setParameter("p_DescriptionText", description);
//
//            storedProcedure.execute();
//
//            session.getTransaction().commit();
//
////            tearDown();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Transactional
//    public List<Description> getDescriptions(){
//        List descriptions;
//
//        try {
//
////            if (sessionFactory == null) {
////                setUp();
////            }
//
//            Session session = sessionFactory.openSession();
//
//            // Using native query for stored procedure execution
//            descriptions = session.createNativeQuery("CALL GetDescriptions", Description.class).list();
//
////            tearDown();
//            return descriptions;
//
////            //setUp();
////            if (sessionFactory == null) {
////                setUp();
////            }
////
////            Session session = sessionFactory.openSession();
////            //session.beginTransaction();
////
////            //NativeQuery<Description> s = new NativeQuery<Description>();
////
////            descriptions = session.createNativeQuery("CALL GetDescriptions()").addEntity(Description.class).list();
////            //.addEntity(Description.class).stream().toList();
////
////            //descriptions = session.createQuery("select d from Description d", Description.class).list();
//////            descriptions.forEach(System.out::println);
////            //session.getTransaction().commit();
////
////            tearDown();
////            return descriptions;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public List<String> getDepartmentsNames(){
//        List names;
//
//        try {
////            if (sessionFactory == null) {
////                setUp();
////            }
//            //setUp();
//
//            Session session = sessionFactory.openSession();         //fixme: zwraca ze session == null
//            names = session.createNativeQuery("CALL GetDepartmentNames()").addEntity(String.class).list();
//            //names = session.createNativeQuery("CALL GetDepartmentNames").addEntity(String.c)
//
////            tearDown();
//            return names;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<Department> getDepartmentsInfo(){
//        List departments;
//
//        try {
////            if (sessionFactory == null){
////                setUp();
////            }
//
//            //setUp();
//
//            Session session = sessionFactory.openSession();                 //fixme: zwraca ze session == null
//            //departments = session.createNativeQuery("CALL GetDepartments()").addEntity(Department.class).list();
//            departments = session.createNativeQuery("CALL GetDepartmentsV2()", Department.class).list();
//
////            tearDown();
//            return departments;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public int authenticateUser(String login, String password) {
//        int empId;
//
//        try {
////            if (sessionFactory == null) {
////                setUp();
////            }
//
//            //setUp();
//
//            Session session = sessionFactory.openSession();
//            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("AuthenticateUser");
//
//            storedProcedure.registerStoredProcedureParameter("p_Login", String.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter("p_Password", String.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.OUT);
//
//            storedProcedure.setParameter("p_Login", login);
//            storedProcedure.setParameter("p_Password", password);
//
//            storedProcedure.execute();
//
//            empId = (int) storedProcedure.getOutputParameterValue("p_EmployeeID");
//
////            tearDown();
//            return empId;
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
