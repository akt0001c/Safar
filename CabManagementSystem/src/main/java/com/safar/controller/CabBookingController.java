package com.safar.controller;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.service.CabBookingService;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PatchMapping("/cabBooking/{cabBookingId}")
    public ResponseEntity<CabBooking> updateCabBookingHandler(@RequestBody CabBooking cabBooking, @PathVariable Integer cabBookingId){
        CabBooking cabBooking1= cabBookingService.updateCabBooking(cabBookingId,cabBooking);
        return ResponseEntity.ok(cabBooking1);
    }

    @GetMapping("/cabBooking/cancel/{cabBookingId}")
    public ResponseEntity<String > cancelCabBookingHandler(@PathVariable Integer cabBookingId){
        String message= cabBookingService.cancelCabBooking(cabBookingId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/cabBooking/history")
    public ResponseEntity<List<CabBooking>> getAllCabBookingHandler(Authentication authentication){
        String email=authentication.getName();
        log.info(email+" user email");
        List<CabBooking> cabBookings= cabBookingService.viewAllTrips(email);
        return ResponseEntity.ok(cabBookings);
    }

}
