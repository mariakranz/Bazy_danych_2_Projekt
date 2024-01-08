package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

@Service
public class FacilitiesManagementService {
    private final DatabaseConnector db = new DatabaseConnector();

    public RoomInfo getRoomInfoById(int id){
        return db.getRoomInfoByID(id);
    }
}
