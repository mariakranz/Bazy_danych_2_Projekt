package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public Description(){

    }

    public Description(String description){
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
