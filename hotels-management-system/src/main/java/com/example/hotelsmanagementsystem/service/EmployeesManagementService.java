package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.EmployeeInfo;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesManagementService {
    private final DatabaseConnector db = new DatabaseConnector();

    public List<String> getAllDepartmentsNames() {
        return db.getDepartmentsNames();
    }

    public List getAllDepartments(){
        return db.getDepartmentsInfo();
    }

    public EmployeeInfo getEmployeeInfoByID(int id){
        return db.getEmployeeInfoByID(id);
    }
}
