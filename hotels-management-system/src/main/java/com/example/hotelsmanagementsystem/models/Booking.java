package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings",
        indexes = {@Index(name = "RoomID_idx", columnList = "RoomID")})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ClientName", length = 255)
    private String clientName;

    @Column(name = "ClientSurname", nullable = false, length = 255)
    private String clientSurname;

    @Column(name = "PhoneNumber", nullable = false, length = 9)
    private String phoneNumber;

    @Column(name = "Email", nullable = false, length = 255)
    private String email;

    @Column(name = "StartDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "EndDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "RoomID", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "RoomID", foreignKeyDefinition = "FOREIGN KEY (RoomID) REFERENCES rooms(id)"))
    private Room room;

    public Booking(){

    }

    public Booking(String clientName, String clientSurname, String phoneNumber,
                   String email, Date startDate, Date endDate, Room room){

        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        // Validate and set the startDate here
        if (startDate != null) {
            this.startDate = startDate;
        } else {
            // Handle the case when startDate is null or invalid
            // For example, set a default date or throw an exception
            throw new IllegalArgumentException("Invalid startDate");
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        // Validate and set the endDate here
        if (endDate != null) {
            this.endDate = endDate;
        } else {
            // Handle the case when endDate is null or invalid
            // For example, set a default date or throw an exception
            throw new IllegalArgumentException("Invalid endDate");
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", room=" + room +
                '}';
    }
}

