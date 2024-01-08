package com.example.hotelsmanagementsystem.service;

import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesManagementService {
    private final DatabaseConnector db = new DatabaseConnector();

//    public RoomInfo getRoomInfoById(int id){
//        return db.getRoomInfoByID(id);
//    }
    public List<RoomInfo> getRoomInfo(){
        return db.getRoomsInfo();
    }
}
