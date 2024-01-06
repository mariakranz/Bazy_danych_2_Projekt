package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "buildings", indexes = {@Index(name = "DescriptionID_idx", columnList = "DescriptionID")})
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "City", nullable = false, length = 255)
    private String city;

    @Column(name = "Street", nullable = false, length = 255)
    private String street;

    @ManyToOne
    @JoinColumn(name = "DescriptionID", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "DescriptionID", foreignKeyDefinition = "FOREIGN KEY (DescriptionID) REFERENCES descriptions(id) ON DELETE SET NULL"))
    private Description description;

    public Building(){

    }

    public Building(String city, String street, Description description){
        this.city = city;
        this.street = street;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}