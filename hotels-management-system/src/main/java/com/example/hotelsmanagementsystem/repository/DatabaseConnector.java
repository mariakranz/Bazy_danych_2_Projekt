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
    public List<BookingRet> getBookings() throws RuntimeException{
        List<BookingRet> bookings = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            StoredProcedureQuery storedProcedure = session.createStoredProcedureQuery("GetBookings");
            storedProcedure.execute();

            List<Object[]> resultList = storedProcedure.getResultList();
            for (Object[] result : resultList) {
                BookingRet booking = new BookingRet(
                        (int) result[0],
                        (String) result[1],
                        (String) result[2],
                        (String) result[3],
                        (String) result[4],
                        (Date) result[5],
                        (Date) result[6],
                        (int) result[7]);
                bookings.add(booking);
            }

            return bookings;
        } catch (Exception e) {
            throw new RuntimeException("Error getting bookings info.", e);
        }
    }
    public int createNewBooking(String clientName, String clientSurname, String phoneNumber,
                                String email, Date startDate, Date endDate, int roomID) throws RuntimeException {
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
            storedProcedure.registerStoredProcedureParameter("p_BookingID", Integer.class, ParameterMode.OUT);

            storedProcedure.setParameter("p_ClientName", clientName);
            storedProcedure.setParameter("p_ClientSurname", clientSurname);
            storedProcedure.setParameter("p_PhoneNumber", phoneNumber);
            storedProcedure.setParameter("p_Email", email);
            storedProcedure.setParameter("p_StartDate", startDate);
            storedProcedure.setParameter("p_EndDate", endDate);
            storedProcedure.setParameter("p_RoomID", roomID);

            storedProcedure.execute();
            return (Integer) storedProcedure.getOutputParameterValue("p_BookingID");

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

    //employees
    public EmployeeInfo getEmployeeInfoByID(int id) throws RuntimeException{
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
    public void updateLastLoginDate(int EmpId) throws RuntimeException {
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
    public List<RoomInfo> getRoomsInfo(int bNumber, String city, String type) throws RuntimeException {
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

}
