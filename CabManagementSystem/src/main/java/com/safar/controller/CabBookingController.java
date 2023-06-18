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

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.exceptions.CabBookingException;
import com.safar.service.CabBookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

=======
>>>>>>> b98ce707673276b7562912424bf31d0f9c34d88d
@RestController
@Slf4j
public class CabBookingController {
<<<<<<< HEAD
	
	@Autowired
   private	CabBookingService cabbookingservice;
	/*
=======
>>>>>>> b98ce707673276b7562912424bf31d0f9c34d88d

    @Autowired
    private CabBookingService cabBookingService;

    @Autowired
    private UserService userService;

<<<<<<< HEAD
	
	@PostMapping("/cabBookings")
	public ResponseEntity<CabBooking> insertCabBookingHandler(  @RequestBody Users user ){
		
		System.out.println(user.getEmail());
//		System.out.println(cabBooking.getFromLocation());
//	
//		CabBooking c= cabbookingservice.insertCabBooking(cabBooking, null);
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
//	@PostMapping("/cabBooking/Id/{userId}")
//	public ResponseEntity<CabBooking> insertCabBookingByUserHandler(@RequestBody CabBooking cabBooking,@PathVariable Integer userId){
//		CabBooking c= cabbookingservice.insertCabBookingByUser(cabBooking, userId);
//		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
//	}
//	
//	@PutMapping("/cabBooking/{cabBookingId}")
//	public ResponseEntity<CabBooking> insertCabBookingHandler(@RequestBody CabBooking cabBooking,@PathVariable Integer cabBookingId){
//		CabBooking c= cabbookingservice.updateCabBooking(cabBookingId, cabBooking);
//		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
//	}
//	
//	@DeleteMapping("/cabBooking/{cabBookingId}")
//	public ResponseEntity<String> deleteCabBookingHandler(@PathVariable Integer cabBookingId){
//		String c= cabbookingservice.deleteCabBooking(cabBookingId);
//		return new ResponseEntity<String>(c, HttpStatus.OK);
//	}
//	
//	@GetMapping("/cabBooking/{userId}")
//	public ResponseEntity<List<CabBooking>> getCabBookingHandler(@PathVariable Integer userId){
//		List<CabBooking> list= cabbookingservice.viewAllTrips(userId);
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
//	
//	@GetMapping("/cabBooking/{userId}/{cabBookingId}")
//	public ResponseEntity<Float> getCabBookingHandler(@PathVariable Integer userId,@PathVariable Integer cabBookingId){
//		Float ans= cabbookingservice.calculateBill(userId, cabBookingId);
//		return new ResponseEntity<>(ans, HttpStatus.OK);
//	}
	
=======
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
>>>>>>> b98ce707673276b7562912424bf31d0f9c34d88d

}
