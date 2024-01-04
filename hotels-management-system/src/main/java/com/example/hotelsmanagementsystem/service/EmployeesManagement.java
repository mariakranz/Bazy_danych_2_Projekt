package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.repository.DatabaseInterface;

import java.util.List;

public class EmployeesManagement implements ManagingDepartments {

    DatabaseInterface _dbInterface;
    @Override
    public List<String> getAll() {
        return _dbInterface.getAllDepartments();
    }
}
