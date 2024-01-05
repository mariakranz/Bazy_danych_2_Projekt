package com.example.hotelsmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @GetMapping({"/index", "/"})
    public ModelAndView getAllEmployees() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("title", "Strona główna");
        return mav;
    }
    @GetMapping({"/offers"})
    public ModelAndView getAllEmployees2() {
        ModelAndView mav = new ModelAndView("offers");
        mav.addObject("title", "Oferty");
        return mav;
    }
}
