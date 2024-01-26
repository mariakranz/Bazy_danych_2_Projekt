package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();
    public int authenticateUser(String login, String password){
        return db.authenticateUser(login, password);
    }
    public void updateLastLoginDate(int EmpId) throws RuntimeException {
        db.updateLastLoginDate(EmpId);
    }
}