package com.safar.service;

import java.util.List;

import com.safar.entity.CabBooking;

public interface CabBookingService {
	
//	insert cabbooking
	public CabBooking insertCabBooking(CabBooking cabbooking);
	
//	insert cabbooking by user
	public CabBooking insertCabBookingByUser(CabBooking cabbooking,Integer userId);
	
//	update cab booking
	public CabBooking updateCabBooking(Integer cabBookingId,CabBooking cabbooking);

//  delete cab booking
	public String deleteCabBooking(Integer cabBookingId);
	
//	view all trips of a particular user
	public List<CabBooking> viewAllTrips(Integer userId);
	
//	calculate bill 
	public Float calculateBill(Integer userId,Integer cabBookingId);

}
