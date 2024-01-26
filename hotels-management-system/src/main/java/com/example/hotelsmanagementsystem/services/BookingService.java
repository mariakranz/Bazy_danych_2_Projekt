package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.BookingRet;
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
                                 String email, Date startDate, Date endDate, int roomID) throws  IllegalArgumentException{

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
        try {
            boolean success = db.deleteBooking(bookingID, employeeID);
            if(success) return TransactionStatus.COMMITTED;

        }catch (RuntimeException ignored){}
        return TransactionStatus.FAILED_COMMIT;
    }

    public List<BookingRet> getAllBookings() throws IllegalArgumentException{
        try{
            return db.getBookings();
        }catch (RuntimeException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
