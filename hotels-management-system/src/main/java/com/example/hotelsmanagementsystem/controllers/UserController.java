package com.example.hotelsmanagementsystem.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public int getUserById(@PathVariable int id) {
        return 7;
    }
}
