package com.example.hotelsmanagementsystem.models;
import jakarta.persistence.*;

@Entity
public class EmployeeInfo {

    @Id
    @Column(name = "EmployeeID")
    private int employeeID;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "EmployeeSurname")
    private String employeeSurname;

    @Column(name = "EmployeePhone")
    private String employeePhone;

    @Column(name = "EmployeeEmail")
    private String employeeEmail;

    @Column(name = "EmployeePrivilege")
    private int employeePrivilege;

    @Column(name = "DepartmentName")
    private String departmentName;

    public EmployeeInfo(){};

    public EmployeeInfo(String employeeName, String employeeSurname, String employeePhone, String employeeEmail, int employeePrivilege, String departmentName){

        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeePrivilege = employeePrivilege;
        this.departmentName = departmentName;
    }

    public EmployeeInfo(int employeeID,String employeeName, String employeeSurname, String employeePhone, String employeeEmail, int employeePrivilege, String departmentName){

        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeePrivilege = employeePrivilege;
        this.departmentName = departmentName;
    }
    // Getters and setters

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getEmployeePrivilege() {
        return employeePrivilege;
    }

    public void setEmployeePrivilege(int employeePrivilege) {
        this.employeePrivilege = employeePrivilege;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName +
                ", employeeSurname='" + employeeSurname  +
                ", employeePhone='" + employeePhone +
                ", employeeEmail='" + employeeEmail +
                ", employeePrivilege=" + employeePrivilege +
                ", departmentName='" + departmentName +
                '}';
    }
}

