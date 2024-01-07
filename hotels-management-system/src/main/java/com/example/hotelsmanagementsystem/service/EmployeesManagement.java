package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.Department;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import com.example.hotelsmanagementsystem.repository.DatabaseInterface;

import java.util.List;

public class EmployeesManagement {
    private final DatabaseConnector db = new DatabaseConnector();

    public List<String> getAllDepartmentsNames() {
        return db.getDepartmentsNames();
    }

    public List<Department> getAllDepartments(){
        return db.getDepartmentsInfo();
    }
}
