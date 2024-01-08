package com.example.hotelsmanagementsystem.models;
import jakarta.persistence.*;

@Entity
public class RoomInfo {

    @Id
    @Column(name = "RoomID")
    private int roomID;

    @Column(name = "Number")
    private int number;

    @Column(name = "Type")
    private String type;

    @Column(name = "BedsNumber")
    private int bedsNumber;

    @Column(name = "RoomDescription")
    private String roomDescription;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "BuildingDescription")
    private String buildingDescription;

    @Column(name = "RoomCount")
    private Long roomCount;

    public RoomInfo(int number, String type, int bedsNumber, String roomDescription,
                    String city, String street, String buildingDescription, Long roomCount){

        this.number = number;
        this.type = type;
        this.bedsNumber = bedsNumber;
        this.roomDescription = roomDescription;
        this.city = city;
        this.street = street;
        this.buildingDescription = buildingDescription;
        this.roomCount = roomCount;
    }

    public RoomInfo(){};

    // Getters and setters

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    public Long getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Long roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "RoomInfo{" +
                "roomID=" + roomID +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", bedsNumber=" + bedsNumber +
                ", roomDescription='" + roomDescription + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingDescription='" + buildingDescription + '\'' +
                ", roomCount=" + roomCount +
                '}';
    }
}

