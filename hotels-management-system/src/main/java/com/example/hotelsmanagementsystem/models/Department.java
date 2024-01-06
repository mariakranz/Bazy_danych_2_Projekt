package com.example.hotelsmanagementsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "departments",
        uniqueConstraints = {@UniqueConstraint(columnNames = "Name", name = "Name_UNIQUE")},
        indexes = {@Index(name = "ManagerID_idx", columnList = "ManagerID")})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

//    @Column(name = "ManagerID", nullable = false, insertable=false, updatable=false)
//    //@Column(insertable=false, updatable=false)
//    private int managerID;

    @ManyToOne
    //@JoinColumn(name = "ManagerID", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JoinColumn(name = "ManagerID", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "ManagerIDfk", foreignKeyDefinition = "FOREIGN KEY (ManagerID) REFERENCES employees(id) ON DELETE RESTRICT ON UPDATE NO ACTION"))
    //@JoinColumn(name = "ManagerID", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "ManagerIDfk", foreignKeyDefinition = "FOREIGN KEY (ManagerID) REFERENCES employees(id)"))
    private Employee manager;

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
