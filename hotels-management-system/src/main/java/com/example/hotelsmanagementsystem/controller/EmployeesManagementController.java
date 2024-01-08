package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.service.EmployeesManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesManagementController {
    private final EmployeesManagementService em = new EmployeesManagementService();
    //ManagingDepartments _departemntMagager;

//    @GetMapping("/departments")
//    public List<String> getAllDepartments() {
//        return _departemntMagager.getAll();
//    }

    @GetMapping("/departments")         //fixme: nie da sie dobic do bazy
    public ResponseEntity<Object> getDepartmentsNames() {
        return new ResponseEntity<>(em.getAllDepartmentsNames(), HttpStatus.OK);
    }

    @GetMapping("/departments/info")    //fixme: nie da sie dobic do bazy
    public ResponseEntity<Object> getDepartmentsInfo() {
        return new ResponseEntity<>(em.getAllDepartments(), HttpStatus.OK);
    }

}
