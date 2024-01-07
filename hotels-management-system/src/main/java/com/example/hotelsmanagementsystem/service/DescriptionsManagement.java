package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.Department;
import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;

import java.util.List;

public class DescriptionsManagement {
    private final DatabaseConnector db = new DatabaseConnector();
    public List<Description> getAllDescriptions(){
        return db.getDescriptions();
    }

    public void addDescriptionToDatabase(Description description){
        //Description newDescription = new Description(text);
        db.saveDescriptionToDB(description);
    }
}
