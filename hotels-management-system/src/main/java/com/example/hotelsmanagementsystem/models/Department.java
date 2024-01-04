package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedStoredProcedureQuery(
        name = "GetDepartmentName",
        procedureName = "GetDepartmentName",
        resultClasses = Department.class,
        parameters = {}
)
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    // Gettery i settery
}