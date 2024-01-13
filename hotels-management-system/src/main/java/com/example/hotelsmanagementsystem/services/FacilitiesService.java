package com.example.hotelsmanagementsystem.services;

import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.repository.DatabaseConnector;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesService {
    private final DatabaseConnector db = DatabaseConnector.getInstance();

//    public RoomInfo getRoomInfoById(int id){
//        return db.getRoomInfoByID(id);
//    }
    public List<RoomInfo> getRoomInfo(int bNumber, String city, String type){
        return db.getRoomsInfo(bNumber, city, type);
    }
}
