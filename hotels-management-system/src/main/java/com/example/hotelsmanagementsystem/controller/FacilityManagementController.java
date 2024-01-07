package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.service.DescriptionsManagement;
import io.swagger.annotations.BasicAuthDefinition;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facilities")
public class FacilityManagementController {
    private final DescriptionsManagement dm = new DescriptionsManagement();
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

}
