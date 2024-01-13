package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionsService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();
    public List<Description> getAllDescriptions(){
        return db.getDescriptions();
    }

    public void addDescriptionToDatabase(String description){
        //Description newDescription = new Description(text);
        db.saveDescriptionToDB(description);
    }
}
