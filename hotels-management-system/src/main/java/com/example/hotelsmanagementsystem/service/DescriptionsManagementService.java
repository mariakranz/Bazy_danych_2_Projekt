package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionsManagementService {
    private final DatabaseConnector db = new DatabaseConnector();
    public List<Description> getAllDescriptions(){
        return db.getDescriptions();
    }

    public void addDescriptionToDatabase(String description){
        //Description newDescription = new Description(text);
        db.saveDescriptionToDB(description);
    }
}
