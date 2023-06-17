package com.safar.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import com.safar.entity.Driver;
import com.safar.entity.Status;
import com.safar.service.DriverService;
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
	private UserRepository userrepo;
	
	@Autowired
	private CabBookingRepository cabbookingrepo;

    @Autowired
    private DriverService driverService;

	@Override
	public CabBooking insertCabBooking(CabBooking cabbooking, String email) {
		if(cabbooking==null) {
			throw new CabBookingException("cabBooking object is null");
		}
        Users user=userrepo.findByEmail(email).orElseThrow(()->new CabBookingException("enter valid email"));
        cabbooking.setUser(user);
//        List<Driver> drivers = driverService.();
		return cabbookingrepo.save(cabbooking);
	}

	@Override
	public CabBooking updateCabBooking(Integer cabBookingId, CabBooking cabbooking) {
		if(cabbooking==null) throw new CabBookingException("cabbooking object is null");
		CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cab booking id"));
        //here we need date and time now we are not getting it from frontend

        if(c.getStatus().equals(Status.Cancelled)) throw new CabBookingException("cab booking is cannot be updated as it is already booked");
        String  distance=  cabbooking.getDistanceInKm()+"";
        int distanceInKm= Integer.parseInt(distance);

		c.setFromDateTime(cabbooking.getFromDateTime());
		c.setToDateTime(LocalDateTime.now().plusMinutes(distanceInKm* 3L));
		c.setToLocation(cabbooking.getToLocation());
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
