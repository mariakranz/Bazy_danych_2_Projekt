package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.service.DescriptionsManagementService;
import com.example.hotelsmanagementsystem.service.FacilitiesManagementService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/facilities")
public class FacilityManagementController {
    private final DescriptionsManagementService dm = new DescriptionsManagementService();
    FacilitiesManagementService fm = new FacilitiesManagementService();
//    @GetMapping("/descriptions")
//    public String getAllDescriptions() {
//
//        return dm.getAllDescriptions();
//    }
    @GetMapping("/descriptions")
    public ResponseEntity<Object> getAllDescriptions() {
        return new ResponseEntity<>(dm.getAllDescriptions(), HttpStatus.OK);
    }

    @PostMapping("/descriptions")       //fixme: nie da sie dobic do tego endpointa
    public HttpStatus addDescription(@RequestBody Description description) {
        //return HttpStatus.OK;
        //dm.addDescriptionToDatabase(description);
        return HttpStatus.ACCEPTED;
        //return "OK";
    }

    @PostMapping("/test")       //fixme: nie da sie dobic do tego endpointa
    public HttpStatus test() {
        return HttpStatus.OK;
    }
    @GetMapping("/ri")
    public ResponseEntity<List<RoomInfo>> getAllRoms() {
        List<RoomInfo> roomInfoList = fm.getRoomInfo();
        return new ResponseEntity<>(roomInfoList, HttpStatus.OK);
    }
}
