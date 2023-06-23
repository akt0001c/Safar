package com.safar.service;

import java.util.List;

import com.safar.entity.CabBooking;

import java.util.List;

import com.safar.entity.CabBooking;

public interface CabBookingService {
	
//	insert CabBooking
	public CabBooking insertCabBooking(CabBooking cabbooking, String email);

//	update cab booking
	public CabBooking updateCabBooking(Integer cabBookingId,CabBooking cabbooking);

//  delete cab booking
	public String cancelCabBooking(Integer cabBookingId);
	
//	view all trips of a particular user
	public List<CabBooking> viewAllTrips(String  email);
	
    public CabBooking completeTrip(Integer cabBookingId , String email);

    public List<CabBooking> viewAllBookendCab();

}
