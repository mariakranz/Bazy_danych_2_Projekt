package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employees",
        uniqueConstraints = {@UniqueConstraint(columnNames = "Phone", name = "Phone_UNIQUE"),
                @UniqueConstraint(columnNames = "Email", name = "Email_UNIQUE")},
        indexes = {@Index(name = "DepartmentID_idx", columnList = "DepartmentID")})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Surname", nullable = false, length = 255)
    private String surname;

    @Column(name = "Phone", nullable = false, length = 9)
    private String phone;

    @Column(name = "Email", nullable = false, length = 255)
    private String email;

    @Column(name = "Privilege", nullable = false)
    private int privilege;

    @ManyToOne
    @JoinColumn(name = "DepartmentID", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "DepartmentID", foreignKeyDefinition = "FOREIGN KEY (DepartmentID) REFERENCES departments(id) ON DELETE SET NULL ON UPDATE NO ACTION"))
    private Department department;

    public Employee(){

    }

    public Employee(String name, String surname, String phone, String email, int privilege, Department department){

        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.privilege = privilege;
        this.department = department;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

