package com.example.hotelsmanagementsystem;

import com.example.hotelsmanagementsystem.models.Description;
import com.example.hotelsmanagementsystem.services.DescriptionsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DescriptionsTest {
    DescriptionsService ds = new DescriptionsService();
    @Test
    protected void getDescriptions(){
        int employeeID = 1;
        List<Description> res = ds.getDescriptions(employeeID);
        res.forEach(System.out::println);
    }
}
