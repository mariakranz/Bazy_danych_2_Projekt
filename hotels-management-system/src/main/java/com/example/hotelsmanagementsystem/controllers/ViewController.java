package com.example.hotelsmanagementsystem.controllers;

import com.example.hotelsmanagementsystem.models.EmployeeInfo;
import com.example.hotelsmanagementsystem.models.RoomInfo;
import com.example.hotelsmanagementsystem.models.BookingRet;
import com.example.hotelsmanagementsystem.services.BookingService;
import com.example.hotelsmanagementsystem.services.EmployeesService;
import com.example.hotelsmanagementsystem.services.FacilitiesService;
import com.example.hotelsmanagementsystem.services.LoginService;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    Integer EmpId = -1;
    EmployeeInfo employee;
    ModelAndView mav;
    List<RoomInfo> roomsInfo;

    int roomId;

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
        FacilitiesService fm = new FacilitiesService();
        roomsInfo = fm.getRoomInfo(0,"", "");
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
        EmployeesService em = new EmployeesService();
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
        RoomInfo roomInfo = roomsInfo.get(Integer.parseInt(room));
        roomId = roomInfo.getRoomID();
        mav.addObject("room", roomInfo);
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
        mav = new ModelAndView("profil");
        mav.addObject("title", "Profil");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        mav.addObject("employee", employee);
        return mav;
    }
    @PostMapping({"/roomFilter"})
    public ModelAndView lEmp(@RequestParam String bNumber, @RequestParam String type,@RequestParam String city) {
        FacilitiesService fm = new FacilitiesService();
        roomsInfo = fm.getRoomInfo(Integer.parseInt(bNumber), city, type);
        mav.getModel().put("rooms", roomsInfo);

        return mav;
    }
    @GetMapping({"/bookRoom"})
    public ModelAndView getBookingForm() {
        mav.getModel().put("showBookingForm", true);
        mav.getModel().put("showRoomDetails", false);
        mav.addObject("bedLogin", false);
        return mav;
    }
    @After("/bookRoom")
    @PostMapping({"/bookedRoom"})
    public ModelAndView bookedRoom(
            @RequestParam String clientName,
            @RequestParam String clientSurname,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String startDate,
            @RequestParam String endDate
            ) {
        System.out.println(startDate);
        BookingService bookingService = new BookingService();
        try{
            Date sDate = dateFormat.parse(startDate);
            Date eDate = dateFormat.parse(endDate);
            bookingService.createBooking(clientName,clientSurname,phoneNumber,email,sDate,eDate, roomId);
            mav.getModel().put("showBookingForm", false);
            mav.addObject("showBookingAccept", true);
            wait(1000);
            mav.getModel().put("showBookingAccept", false);


        } catch (Exception e) {
            mav.addObject("ifBookingError", true);
            mav.addObject("bookingError", e.getMessage());
        }

        return mav;
    }
    @GetMapping({"/closeBookingForm"})
    public ModelAndView closeBooking() {

        mav.getModel().put("showBookingForm", false);
        return mav;
    }

    @GetMapping("/hideBookingAccept")
    public ModelAndView hideBookingA() {

        mav.getModel().put("showBookingAccept", false);
        return mav;
    }
    @GetMapping( "/panel")
    public ModelAndView getPanelPage() {
        mav = new ModelAndView("panel");
        mav.addObject("title", "Panel zarządzania");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        mav.addObject("bookings", getBookings());
        return mav;
    }
    @GetMapping( "/deleteBooking")
    public ModelAndView deleteBooking(@RequestParam String bookingId) {
        BookingService bookingService = new BookingService();
        bookingService.deleteBooking(Integer.parseInt(bookingId), EmpId);
        mav = new ModelAndView("panel");
        mav.addObject("title", "Panel zarządzania");
        mav.addObject("EmpId", EmpId);
        mav.addObject("showLoginForm", false);
        mav.addObject("bookings", getBookings());
        return mav;
    }

    private List<BookingRet> getBookings(){
        BookingService bookingService = new BookingService();
        List<BookingRet> sortedBookings = bookingService.getAllBookings()
                .stream()
                .sorted((booking1, booking2) -> booking2.getStartDate().compareTo(booking1.getStartDate()))
                .collect(Collectors.toList());
        return sortedBookings;
    }
}
