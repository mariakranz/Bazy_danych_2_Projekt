package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms",
        indexes = {@Index(name = "BuildingID_idx", columnList = "BuildingID"),
                @Index(name = "DescriptionID_idx", columnList = "DescriptionID")})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Number", nullable = false)
    private int number;

    @Column(name = "Type", length = 255, nullable = true)
    private String type;

    @Column(name = "BedsNumber", nullable = true)
    private Integer bedsNumber;

    @ManyToOne
    @JoinColumn(name = "BuildingID", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "BuildingID", foreignKeyDefinition = "FOREIGN KEY (BuildingID) REFERENCES buildings(id) ON DELETE RESTRICT ON UPDATE NO ACTION"))
    private Building building;

    @ManyToOne
    @JoinColumn(name = "DescriptionID", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "DescriptionIDgfk", foreignKeyDefinition = "FOREIGN KEY (DescriptionID) REFERENCES descriptions(id) ON DELETE SET NULL"))
    private Description description;

    public Room(){

    }

    public Room(int number, String type, Integer bedsNumber, Building building, Description description){

        this.number = number;
        this.type = type;
        this.bedsNumber = bedsNumber;
        this.building = building;
        this.description = description;
    }


    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(Integer bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
