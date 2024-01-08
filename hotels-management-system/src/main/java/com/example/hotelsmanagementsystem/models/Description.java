package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "descriptions", uniqueConstraints = {@UniqueConstraint(columnNames = "Description", name = "Description_UNIQUE")})
public class Description implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Description", nullable = false, length = 255, unique = true)
    private String description;

    public Description(){

    }

    public Description(String description){
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", description=" + description +
                '}';
    }
}
