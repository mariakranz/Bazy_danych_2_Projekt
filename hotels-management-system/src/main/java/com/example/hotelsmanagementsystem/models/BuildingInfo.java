package com.example.hotelsmanagementsystem.models;
import jakarta.persistence.*;

@Entity
public class BuildingInfo {

    @Id
    @Column(name = "BuildingID")
    private int buildingID;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "Description")
    private String description;

    @Column(name = "RoomCount")
    private Long roomCount;

    public BuildingInfo(){

    }

    public BuildingInfo(String city, String street, String description, Long roomCount){

        this.city = city;
        this.street = street;
        this.description = description;
        this.roomCount = roomCount;
    }
    // Getters and setters

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Long roomCount) {
        this.roomCount = roomCount;
    }
}
