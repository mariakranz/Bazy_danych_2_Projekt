package com.example.hotelsmanagementsystem.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthCheck  {
    @GetMapping
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/sth")
    public int getUserById(int id) {
        return id;
    }
}
