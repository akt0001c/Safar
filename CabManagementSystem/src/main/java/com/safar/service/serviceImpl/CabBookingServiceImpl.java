package com.safar.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.exceptions.CabBookingException;
import com.safar.repository.CabBookingRepository;
import com.safar.repository.UserRepository;
import com.safar.service.CabBookingService;

@Service
public class CabBookingServiceImpl implements CabBookingService{
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	CabBookingRepository cabbookingrepo; 

	@Override
	public CabBooking insertCabBooking(CabBooking cabbooking) {
		if(cabbooking==null) {
			throw new CabBookingException("cabbooking object is null");
		}
		return cabbookingrepo.save(cabbooking);
	}

	@Override
	public CabBooking updateCabBooking(Integer cabBookingId, CabBooking cabbooking) {
		if(cabbooking==null) throw new CabBookingException("cabbooking object is null");
		CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cab booking id"));
		c.setFromDateTime(cabbooking.getFromDateTime());
		c.setToDateTime(cabbooking.getToDateTime());
		c.setFromLocation(cabbooking.getFromLocation());
		c.setToLocation(cabbooking.getToLocation());
		c.setStatus(cabbooking.getStatus());
		c.setDistanceInKm(cabbooking.getDistanceInKm());
		c.setBill(cabbooking.getBill());
		return cabbookingrepo.save(c);
	}

	@Override
	public String deleteCabBooking(Integer cabBookingId) {
		CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cabbooking id"));
		cabbookingrepo.delete(c);
		return c.getCabBookingId()+" cabbooking is deleted successfully";
	}

	@Override
	public List<CabBooking> viewAllTrips(Integer userId) {
		Users user=userrepo.findById(userId).orElseThrow(()-> new CabBookingException("enter valid userId"));
		List<CabBooking> list=user.getCabBookings();
		if(list.isEmpty()) throw new CabBookingException("user did not book any cab");
		return list;
	}

	@Override
	public CabBooking insertCabBookingByUser(CabBooking cabbooking, Integer userId) {
		Users user=userrepo.findById(userId).orElseThrow(()-> new CabBookingException("enter valid userId"));
		user.getCabBookings().add(cabbooking);
		if(cabbooking==null) throw new CabBookingException("cabbooking object is null");
		cabbooking.setUser(user);
		return cabbookingrepo.save(cabbooking);
	}

	@Override
	public Float calculateBill(Integer userId,Integer cabBookingId) {
		Users user=userrepo.findById(userId).orElseThrow(()-> new CabBookingException("enter valid userId"));
		List<CabBooking> list=user.getCabBookings();
		CabBooking cab=null;
		for(CabBooking c:list) {
			if(c.getCabBookingId()==cabBookingId) {
				cab=c;
			}
		}
		if(cab==null) {
			throw new CabBookingException(userId+" user dont have booking in "+cabBookingId);
		}
		else {
			return cab.getBill();
		}
	}

}
