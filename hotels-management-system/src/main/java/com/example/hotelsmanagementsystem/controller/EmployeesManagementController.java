package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.service.ManagingDepartments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesManagementController {
    ManagingDepartments _departemntMagager;

    @GetMapping("/departments")
    public List<String> getAllDepartments() {
        return _departemntMagager.getAll();
    }

}
