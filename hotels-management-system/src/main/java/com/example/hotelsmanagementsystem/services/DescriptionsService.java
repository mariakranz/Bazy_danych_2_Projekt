package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionsService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();

    public List<Description> getDescriptions(int employeeID){
        try{
            return db.getDescriptions(employeeID);
        }catch (RuntimeException exception){
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

}
