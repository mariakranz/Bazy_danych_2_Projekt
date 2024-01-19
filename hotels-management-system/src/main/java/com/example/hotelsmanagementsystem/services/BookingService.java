package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.Booking;
import com.example.hotelsmanagementsystem.repository.BookingRet;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();


    public boolean createBooking(String clientName, String clientSurname, String phoneNumber,
                                 String email, Date startDate, Date endDate, int roomID) throws  IllegalArgumentException, RuntimeException{

        if (endDate.before(startDate)) throw new IllegalArgumentException("Błąd rezerwacji - Data końca rezerwacji nie może być przed datą początku.");

        LocalDate localDate = LocalDate.now();
        if (startDate.before(java.sql.Date.valueOf(localDate))) throw new IllegalArgumentException("Błąd rezerwacji - Data początkowa nie może być przed " + localDate + ".");

        if(!email.contains("@"))throw new IllegalArgumentException("Błąd rezerwacji - podano błędny adres e-mail.");
        try {
            return db.createNewBooking(clientName, clientSurname, phoneNumber, email, startDate, endDate, roomID);

        }catch (RuntimeException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    public TransactionStatus deleteBooking(int bookingID, int employeeID){
    //public TransactionStatus deleteBooking(int iD)throws IndexOutOfBoundsException{
        try {
            boolean success = db.deleteBooking(bookingID, employeeID);
            if(success) return TransactionStatus.COMMITTED;
            //else throw new IndexOutOfBoundsException("Booking id not found.");

        }catch (RuntimeException ignored){};
        return TransactionStatus.FAILED_COMMIT;
    }

    //public TransactionStatus updateBookingEmail(int bookingID, String email) throws IndexOutOfBoundsException{
    public TransactionStatus updateBookingEmail(int bookingID, String email, int employeeID){
        try {
            boolean success = db.updateBookingEmail(bookingID, email, employeeID);
            if(success) return TransactionStatus.COMMITTED;

            //else throw new IndexOutOfBoundsException("Booking id not found.");
        }catch (RuntimeException ignored){};
        return TransactionStatus.FAILED_COMMIT;
    }

    public List<BookingRet> getAllBookings() throws IllegalArgumentException{
        try{
            return db.getBookings();
        }catch (RuntimeException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

//    public Map<TransactionStatus, String> updateBookingEmailV3(int bookingID, String email) throws IndexOutOfBoundsException{
//        Map<TransactionStatus, String> response = new HashMap<>(1);
//
//        try {
//            boolean success = db.updateBookingEmail(bookingID, email);
//
//            if(success){
//                response.put(TransactionStatus.COMMITTED, "Email updated.");
//            } else{
//                response.put(TransactionStatus.FAILED_COMMIT, "Email not updated. Booking not found.");
//            }
//        }catch (RuntimeException exception){
//            response.put(TransactionStatus.FAILED_COMMIT, exception.getMessage());
//        };
//        return response;
//    }

//    public boolean updateBookingEmailV2(int bookingID, String email){
//        try {
//            return db.updateBookingEmail(bookingID, email);
//        }catch (RuntimeException ignored){}
//        return false;
//    }
}
