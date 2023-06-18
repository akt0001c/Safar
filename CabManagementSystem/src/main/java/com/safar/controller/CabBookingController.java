package com.safar.controller;

import java.util.List;

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
import com.safar.service.CabBookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class CabBookingController {
	
	@Autowired
	CabBookingService cabbookingservice;
	/*

	{
	  "fromLocation": "delhi",
	  "toLocation":"kanpur",
	  "distanceInKm":50.0,
	  "bill":500.0
	}

	 */

	
	@PostMapping("/cabBooking/{email}")
	public ResponseEntity<CabBooking> insertCabBookingHandler( @RequestBody CabBooking cabBooking ,@PathVariable String email){
		CabBooking c= cabbookingservice.insertCabBooking(cabBooking, email);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cabBooking/Id/{userId}")
	public ResponseEntity<CabBooking> insertCabBookingByUserHandler(@RequestBody CabBooking cabBooking,@PathVariable Integer userId){
		CabBooking c= cabbookingservice.insertCabBookingByUser(cabBooking, userId);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/cabBooking/{cabBookingId}")
	public ResponseEntity<CabBooking> insertCabBookingHandler(@RequestBody CabBooking cabBooking,@PathVariable Integer cabBookingId){
		CabBooking c= cabbookingservice.updateCabBooking(cabBookingId, cabBooking);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/cabBooking/{cabBookingId}")
	public ResponseEntity<String> deleteCabBookingHandler(@PathVariable Integer cabBookingId){
		String c= cabbookingservice.deleteCabBooking(cabBookingId);
		return new ResponseEntity<String>(c, HttpStatus.OK);
	}
	
	@GetMapping("/cabBooking/{userId}")
	public ResponseEntity<List<CabBooking>> getCabBookingHandler(@PathVariable Integer userId){
		List<CabBooking> list= cabbookingservice.viewAllTrips(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/cabBooking/{userId}/{cabBookingId}")
	public ResponseEntity<Float> getCabBookingHandler(@PathVariable Integer userId,@PathVariable Integer cabBookingId){
		Float ans= cabbookingservice.calculateBill(userId, cabBookingId);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}
	

}
