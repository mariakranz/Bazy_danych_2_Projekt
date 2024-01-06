package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.service.DescriptionsManagement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityManagementController {
    private final DescriptionsManagement dm = new DescriptionsManagement();
    @GetMapping("/descriptions")
    public String getAllDescriptions() {

        return dm.getAllDescriptions();
    }

    @GetMapping("/descriptions/post")
    public void addDescription(@RequestParam("txt") String txt) {
        dm.addDescriptionToDatabase(txt);
        //return "OK";
    }
}
