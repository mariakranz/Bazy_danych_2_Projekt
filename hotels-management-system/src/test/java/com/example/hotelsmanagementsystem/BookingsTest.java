package com.example.hotelsmanagementsystem;

import com.example.hotelsmanagementsystem.services.BookingService;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingsTest {
    BookingService bookingService = new BookingService();

    @Test
    protected void createReservation(){

        boolean res = bookingService.createBooking("javatestname", "javatestsurname", "987654321", "sth@sth", convertStringToSqlDate("2024-03-15"), convertStringToSqlDate("2024-03-22"), 15);
        assertFalse(res);
        boolean res2 = bookingService.createBooking("javatestname", "javatestsurname", "987654321", "sth@sth", convertStringToSqlDate("2024-03-23"), convertStringToSqlDate("2024-03-27"), 15);
        assertFalse(res2);
        boolean res3 = bookingService.createBooking("javatestname", "javatestsurname", "987654321", "sth@sth", convertStringToSqlDate("2024-03-21"), convertStringToSqlDate("2024-03-24"), 15);
        assertFalse(res3);
        boolean res4 = bookingService.createBooking("javatestname", "javatestsurname", "987654321", "sth@sth", convertStringToSqlDate("2024-03-4"), convertStringToSqlDate("2024-03-6"), 15);
        assertTrue(res4);
    }

    @Test
    protected void deleteReservation(){
        int bookingID = 7;
        int employeeID = 1;
        TransactionStatus res = bookingService.deleteBooking(bookingID,employeeID );
        assertEquals(TransactionStatus.COMMITTED, res);

        res = bookingService.deleteBooking(bookingID, employeeID);
        assertEquals(TransactionStatus.FAILED_COMMIT, res);
    }

    @Test
    protected void updateEmail(){
        int employeeID = 1;
        TransactionStatus res = bookingService.updateBookingEmail(9, "sthUpdated@email.com", employeeID);
        assertEquals(TransactionStatus.COMMITTED, res);
        res = bookingService.updateBookingEmail(122, "sthUpdated@email.com", employeeID);
        assertEquals(TransactionStatus.FAILED_COMMIT, res);
    }

    private static java.sql.Date convertStringToSqlDate(String dateString) {
        try {
            // Utworzenie obiektu SimpleDateFormat dla formatu yyyy-MM-dd
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Parsowanie stringa do obiektu java.util.Date
            java.util.Date utilDate = sdf.parse(dateString);

            // Konwersja java.util.Date do java.sql.Date
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Obsługa błędów parsowania
            return null;
        }
    }
}
