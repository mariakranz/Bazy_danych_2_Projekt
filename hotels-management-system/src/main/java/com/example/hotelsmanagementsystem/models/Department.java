package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
//@NamedStoredProcedureQuery(
//        name = "GetDepartmentName",
//        procedureName = "GetDepartmentName",
//        resultClasses = Department.class,
//        parameters = {}
//)
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ManagerID")
    private Long managerId;

    public Department(){

    }

    public Department(String name, Long managerId){
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }
    // Gettery i settery
}