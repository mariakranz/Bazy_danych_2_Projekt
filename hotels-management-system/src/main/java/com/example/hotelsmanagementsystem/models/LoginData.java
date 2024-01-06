package com.example.hotelsmanagementsystem.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logindata",
        uniqueConstraints = {@UniqueConstraint(columnNames = "Login", name = "Login_UNIQUE")},
        indexes = {@Index(name = "EmployeeID_idx", columnList = "EmployeeID")})
public class LoginData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Login", nullable = false, length = 255)
    private String login;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "LastLoginDate", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date lastLoginDate;

//    @Column(name = "EmployeeID", nullable = false)
//    private int employeeID;

    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "EmployeeID", foreignKeyDefinition = "FOREIGN KEY (EmployeeID) REFERENCES employees(id) ON DELETE CASCADE ON UPDATE NO ACTION"))
    private Employee employee;

    public LoginData(){

    }

    public  LoginData(String login, String password, Date lastLoginDate, Employee employee){

        this.login = login;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.employee = employee;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

