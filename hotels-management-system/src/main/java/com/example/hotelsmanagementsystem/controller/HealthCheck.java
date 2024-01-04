package com.example.hotelsmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthCheck  {
    @GetMapping
    public String healthCheck() {
        return "NIE";
    }

    @GetMapping("/sth")
    public int getUserById(int id) {
        return id;
    }
}
