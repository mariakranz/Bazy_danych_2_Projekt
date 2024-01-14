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

public final class DatabaseConnector{
    private static DatabaseConnector instance;
    private final SessionFactory sessionFactory;

    public DatabaseConnector(){
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

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    //bookings
    public boolean createNewBooking (String clientName, String clientSurname, String phoneNumber,
                                     String email, Date startDate, Date endDate, int roomID) throws RuntimeException{
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
            storedProcedure.registerStoredProcedureParameter("p_Success", Boolean.class, ParameterMode.OUT);

            storedProcedure.setParameter("p_ClientName", clientName);
            storedProcedure.setParameter("p_ClientSurname", clientSurname);
            storedProcedure.setParameter("p_PhoneNumber", phoneNumber);
            storedProcedure.setParameter("p_Email", email);
            storedProcedure.setParameter("p_StartDate", startDate);
            storedProcedure.setParameter("p_EndDate", endDate);
            storedProcedure.setParameter("p_RoomID", roomID);

            storedProcedure.execute();
            return (Boolean) storedProcedure.getOutputParameterValue("p_Success");

        } catch (Exception e) {
            throw new RuntimeException("Error creating booking.", e);
        }
    }
    public boolean deleteBooking (int bookingID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("DeleteBookingValidate");
            storedProcedure.registerStoredProcedureParameter("p_BookingID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("p_BookingID", bookingID);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.execute();
            System.out.println(storedProcedure.getUpdateCount());

            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true

        }catch (Exception e){
            throw new RuntimeException("Error deleting booking.");
        }
    }
    public boolean updateBookingEmail (int bookingID, String newEmail, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateBookingEmailValidate");
            storedProcedure.registerStoredProcedureParameter("p_BookingID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_NewEmail", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("p_BookingID", bookingID);
            storedProcedure.setParameter("p_NewEmail", newEmail);
            storedProcedure.setParameter("p_EmployeeID", employeeID);

            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true

        }catch (Exception e){
            throw new RuntimeException("Error deleting booking.");
        }
    }

    //buildings
    public boolean insertBuilding(String city, String street, int descriptionID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("InsertBuildingValidate");
            storedProcedure.registerStoredProcedureParameter("p_City", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_Street", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_DescriptionID", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("p_City", city);
            storedProcedure.setParameter("p_Street", street);
            storedProcedure.setParameter("p_DescriptionID", descriptionID);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error creating building.", e);
        }
    }
    //departments

    //descriptions
    public List<Description> getDescriptions(int employeeID) throws RuntimeException{
        List<Description> descriptions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetDescriptionsValidatePrivilege");
            storedProcedure.registerStoredProcedureParameter("employeeID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("employeeID", employeeID);
            storedProcedure.execute();

            List<Object[]> resultList = storedProcedure.getResultList();
            for (Object[] result : resultList) {
                Description description = new Description((int)result[0], (String) result[1]);
                descriptions.add(description);
            }

            return descriptions;
        } catch (Exception e) {
            throw new RuntimeException("Error getting rooms info.", e);
        }
    }
    public boolean insertDescription(int employeeID, String description) throws RuntimeException{
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("InsertDescriptionValidatePrivilege");
            storedProcedure.registerStoredProcedureParameter("p_DescriptionText", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("p_DescriptionText", description);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error creating description.", e);
        }
    }
    public boolean updateDescription (int buildingID, int descriptionID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateBookingEmailValidate");
            storedProcedure.registerStoredProcedureParameter("p_BuildingID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_NewDescriptionID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("p_BuildingID", buildingID);
            storedProcedure.setParameter("p_NewDescriptionID", descriptionID);
            storedProcedure.setParameter("p_EmployeeID", employeeID);

            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true

        }catch (Exception e){
            throw new RuntimeException("Error deleting booking.");
        }
    }
    public boolean deleteDescription(int descriptionID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("DeleteDescriptionValidate");
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_DescriptionID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.setParameter("p_DescriptionID", descriptionID);
            storedProcedure.execute();
            System.out.println(storedProcedure.getUpdateCount());

            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true

        }catch (Exception e){
            throw new RuntimeException("Error deleting description.");
        }
    }

    //employees
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

    //login
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

    //rooms
    public List<RoomInfo> getRoomsInfo(int bNumber, String city, String type) {
        Byte filterType = 0b000;
        if (bNumber > 0){
            filterType = (byte) (filterType | (1 << 2));
        }
        if (!city.isEmpty()){
            filterType = (byte) (filterType | (1 << 1));
        }
        if (!type.isEmpty()){
            filterType = (byte) (filterType | (1));
        }
        System.out.println(filterType);
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetRoomsInfo");
            storedProcedure.registerStoredProcedureParameter("filterType", Byte.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("bNumber", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("city", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("roomType", String.class, ParameterMode.IN);

            storedProcedure.setParameter("filterType", filterType);
            storedProcedure.setParameter("bNumber", bNumber);
            storedProcedure.setParameter("city", city);
            storedProcedure.setParameter("roomType", type);
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

    public boolean insertRoom(int roomNumber, String roomType,int bedsNumber, int buildingID,
                              int descriptionID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("InsertRoomValidate");
            storedProcedure.registerStoredProcedureParameter("p_RoomNumber", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_RoomType", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_BedsNumber", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_BuildingID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_DescriptionID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);

            storedProcedure.setParameter("p_RoomNumber", roomNumber);
            storedProcedure.setParameter("p_RoomType", roomType);
            storedProcedure.setParameter("p_BedsNumber", bedsNumber);
            storedProcedure.setParameter("p_BuildingID", buildingID);
            storedProcedure.setParameter("p_DescriptionID", descriptionID);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error creating room.", e);
        }
    }

    public boolean deleteRoom(int roomID, int employeeID) throws RuntimeException{
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("DeleteRoomValidate");
            storedProcedure.registerStoredProcedureParameter("p_RoomID", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_EmployeeID", Integer.class, ParameterMode.IN);
            storedProcedure.setParameter("p_RoomID", roomID);
            storedProcedure.setParameter("p_EmployeeID", employeeID);
            storedProcedure.execute();
            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true

        }catch (Exception e){
            throw new RuntimeException("Error deleting booking.");
        }
    }


//    public boolean updateBookingEmail (int bookingID, String newEmail) throws RuntimeException{
//        try (Session session = sessionFactory.openSession()){
//            session.beginTransaction();
//            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("UpdateBookingEmail");
//            storedProcedure.registerStoredProcedureParameter(0, Integer.class, ParameterMode.IN);
//            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//            storedProcedure.setParameter(0, bookingID);
//            storedProcedure.setParameter(1, newEmail);
//
//            storedProcedure.execute();
//            return storedProcedure.getUpdateCount() > 0;    //jesli zmienila sie liczba rekordow zwroc true
//
//        }catch (Exception e){
//            throw new RuntimeException("Error deleting booking.");
//        }
//    }

    //    static {
//        try {
//            // Initialize the SessionFactory once
//            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                    .configure()
//                    .build();
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            throw new RuntimeException("Error initializing Hibernate SessionFactory", e);
//        }
//    }
//    protected void createEntityManager(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.close();
//        entityManagerFactory.close();
//    }

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
