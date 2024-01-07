package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final DatabaseConnector db = new DatabaseConnector();


    public int authenticateUser(String login, String password) {
        return db.authenticateUser(login, password);
    }
}