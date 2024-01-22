package com.example.hotelsmanagementsystem.models;

import com.example.hotelsmanagementsystem.models.Room;

import java.util.Date;

public class BookingRet {
    private int id;
    private String clientName;
    private String clientSurname;
    private String phoneNumber;

    private String email;
    private Date startDate;
    private Date endDate;
    private int roomID;

    @Override
    public String toString() {
        return "BookingRet{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", roomID=" + roomID +
                '}';
    }

    public BookingRet(int id, String clientName, String clientSurname, String phoneNumber,
                      String email, Date startDate, Date endDate, int roomID){
        this.id = id;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
