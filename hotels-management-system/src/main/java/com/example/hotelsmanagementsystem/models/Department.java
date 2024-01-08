package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

import java.io.Serializable;

//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name = "callGetDepartments",
//                query = "CALL GetDepartments()",
//                resultClass = Department.class)
//})
@Entity
@Table(name = "departments",
        uniqueConstraints = {@UniqueConstraint(columnNames = "Name", name = "Name_UNIQUE")},
        indexes = {@Index(name = "ManagerID_idx", columnList = "ManagerID")})
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ManagerID", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "ManagerIDfk", foreignKeyDefinition = "FOREIGN KEY (ManagerID) REFERENCES employees(id) ON DELETE RESTRICT ON UPDATE NO ACTION"))
   private Employee manager;

    public Department(){

    }

    public  Department(int id, String name, Employee manager){
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public  Department(String name, Employee manager){

        this.name = name;
        this.manager = manager;
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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
