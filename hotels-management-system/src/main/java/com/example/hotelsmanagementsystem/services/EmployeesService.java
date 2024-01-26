package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.EmployeeInfo;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();

    public EmployeeInfo getEmployeeInfoByID(int id) throws RuntimeException{
        return db.getEmployeeInfoByID(id);
    }
}
