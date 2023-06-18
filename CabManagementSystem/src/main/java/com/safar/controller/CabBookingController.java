package com.safar.controller;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.service.CabBookingService;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CabBookingController {

    @Autowired
    private CabBookingService cabBookingService;

    @Autowired
    private UserService userService;

    @PostMapping("/cabBooking")
    public ResponseEntity<CabBooking> saveCabBookingHandler(@RequestBody CabBooking cabBooking, Authentication authentication){

        String email=authentication.getName();
        log.info(email+"user email");

        Users user= userService.getUserDetailsByEmail(email);
        CabBooking cabBooking1= cabBookingService.insertCabBooking(cabBooking,user.getEmail());
        return ResponseEntity.ok(cabBooking1);
    }


}
