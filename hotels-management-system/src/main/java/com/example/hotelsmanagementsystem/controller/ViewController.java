package com.example.hotelsmanagementsystem.controller;

import com.example.hotelsmanagementsystem.models.Employee;
import com.example.hotelsmanagementsystem.models.EmployeeInfo;
import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.service.EmployeesManagementService;
import com.example.hotelsmanagementsystem.service.FacilitiesManagementService;
import com.example.hotelsmanagementsystem.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {
    Integer EmpId = -1;
    EmployeeInfo employee;
    ModelAndView mav;
    List<RoomInfo> roomsInfo;

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
        FacilitiesManagementService fm = new FacilitiesManagementService();
        roomsInfo = fm.getRoomInfo();
        mav = new ModelAndView("offers");
        mav.addObject("title", "Oferty");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        mav.addObject("rooms", roomsInfo);

        System.out.println(roomsInfo.get(0).getType());
        return mav;
    }
    @GetMapping({"/openLoginForm"})
    public ModelAndView getLoginForm() {
        mav.getModel().put("showLoginForm", true);
        mav.addObject("bedLogin", false);
        return mav;
    }
    @PostMapping({"/lEmp"})
    public ModelAndView lEmp(@RequestParam String username, @RequestParam String password) {
        LoginService loginService = new LoginService();
        EmployeesManagementService em = new EmployeesManagementService();
        EmpId = loginService.authenticateUser(username, password);
        if(EmpId == -1){
            mav.getModel().put("badLogin", true);
        }else{
            loginService.updateLastLoginDate(EmpId);
            employee = em.getEmployeeInfoByID(EmpId);
            mav.getModel().put("EmpId", EmpId);
            mav.getModel().put("showLoginForm", false);
        }

        return mav;
    }
    @GetMapping({"/closeLoginForm"})
    public ModelAndView closeLogin() {

        mav.getModel().put("showLoginForm", false);
        mav.getModel().put("badLogin", false);
        return mav;
    }
    @GetMapping({"/logoutEmp"})
    public ModelAndView logoutEmp() {
        EmpId = -1;
        mav.getModel().put("EmpId", EmpId);
        return mav;
    }
    @GetMapping({"/roomInfo"})
    public ModelAndView roomInfo(@RequestParam String room) {
        mav.addObject("room", roomsInfo.get(Integer.parseInt(room)));
        mav.addObject("showRoomDetails", true);
        return mav;
    }
    @GetMapping({"/closeRoomDetails"})
    public ModelAndView closeRoomInfo() {
        mav.getModel().put("showRoomDetails", false);
        return mav;
    }
    @GetMapping({"/profil"})
    public ModelAndView getProfilPage() {
        FacilitiesManagementService fm = new FacilitiesManagementService();
        roomsInfo = fm.getRoomInfo();
        mav = new ModelAndView("profil");
        mav.addObject("title", "Profil");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        mav.addObject("employee", employee);
        return mav;
    }
}
