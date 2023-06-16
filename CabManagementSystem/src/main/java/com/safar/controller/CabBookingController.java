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

@RestController
public class CabBookingController {
	
	@Autowired
	CabBookingService cabbookingservice;
	
//	{
//		  "fromLocation": "dharmapuri",
//		  "toLocation":"erode",
//		  "fromDateTime": "2023-05-21-03-30-30",
//		  "toDateTime": "2023-05-22-05-30-30",
//		  "status": "Cancelled",
//		  "distanceInKm":50.0,
//		  "bill":500.0
//		}
	
	@PostMapping("/cabbooking")
	public ResponseEntity<CabBooking> insertCabBookingHandler(@RequestBody CabBooking cabooking){
		CabBooking c= cabbookingservice.insertCabBooking(cabooking);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cabbooking/{userId}")
	public ResponseEntity<CabBooking> insertCabBookingByUserHandler(@RequestBody CabBooking cabooking,@PathVariable Integer userId){
		CabBooking c= cabbookingservice.insertCabBookingByUser(cabooking, userId);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/cabbooking/{cabBookingId}")
	public ResponseEntity<CabBooking> insertCabBookingHandler(@RequestBody CabBooking cabooking,@PathVariable Integer cabBookingId){
		CabBooking c= cabbookingservice.updateCabBooking(cabBookingId, cabooking);
		return new ResponseEntity<CabBooking>(c, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/cabbooking/{cabBookingId}")
	public ResponseEntity<String> deleteCabBookingHandler(@PathVariable Integer cabBookingId){
		String c= cabbookingservice.deleteCabBooking(cabBookingId);
		return new ResponseEntity<String>(c, HttpStatus.OK);
	}
	
	@GetMapping("/cabbooking/{userId}")
	public ResponseEntity<List<CabBooking>> getCabBookingHandler(@PathVariable Integer userId){
		List<CabBooking> list= cabbookingservice.viewAllTrips(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/cabbooking/{userId}/{cabBookingId}")
	public ResponseEntity<Float> getCabBookingHandler(@PathVariable Integer userId,@PathVariable Integer cabBookingId){
		Float ans= cabbookingservice.calculateBill(userId, cabBookingId);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}
	

}
