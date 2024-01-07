package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    Integer EmpId = -1;
    ModelAndView mav;
    @GetMapping( "/")
    public ModelAndView getIndexPage() {
        mav = new ModelAndView("index");
        mav.addObject("title", "Strona główna");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        return mav;
    }
    @GetMapping({"/offers"})
    public ModelAndView getOffersPage() {
        mav = new ModelAndView("offers");
        mav.addObject("title", "Oferty");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        return mav;
    }
    @GetMapping({"/openLoginForm"})
    public ModelAndView getLoginForm() {
        mav.getModel().put("showLoginForm", true);
        return mav;
    }
    @PostMapping({"/lEmp"})
    public ModelAndView lEmp(@RequestParam String username, @RequestParam String password) {
        LoginService loginService = new LoginService();
        EmpId = loginService.authenticateUser(username, password);
        System.out.println(EmpId);
        mav.getModel().put("EmpId", EmpId);
        mav.getModel().put("showLoginForm", false);
        return mav;
    }
    @GetMapping({"/closeLoginForm"})
    public ModelAndView closeLogin() {

        mav.getModel().put("showLoginForm", false);
        return mav;
    }
    @GetMapping({"/logoutEmp"})
    public ModelAndView logoutEmp() {
        EmpId = -1;
        mav.getModel().put("EmpId", EmpId);
        return mav;
    }
}
