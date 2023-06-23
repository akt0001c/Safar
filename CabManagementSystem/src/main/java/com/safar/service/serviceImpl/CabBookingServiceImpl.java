package com.safar.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.safar.entity.*;
import com.safar.service.DriverService;
import com.safar.service.WalletServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safar.exceptions.CabBookingException;
import com.safar.repository.CabBookingRepository;
import com.safar.repository.UserRepository;
import com.safar.service.CabBookingService;

@Service
@Slf4j
public class CabBookingServiceImpl implements CabBookingService{
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private CabBookingRepository cabbookingrepo;

    @Autowired
    private DriverService driverService;

    @Autowired
    private WalletServices walletServices;

	@Override
	public CabBooking insertCabBooking(CabBooking cabbooking, String email) {
		if(cabbooking==null) {
			throw new CabBookingException("cabBooking object is null");
		}
        Users user=userrepo.findByEmail(email).orElseThrow(()->new CabBookingException("enter valid email"));

        List<Driver> drivers = driverService.findAllDrivers();
     

       if(drivers.isEmpty()) {
           throw new CabBookingException("All  drivers  are not available");
       }

         List<Driver> newDriver = drivers.stream().filter((driver) -> driver.getNewLocation().
                equals(cabbooking.getFromLocation())).filter((s) -> s.getStatus().equals(DriverStatus.Available)).toList();


       newDriver.forEach((driver) -> log.info(driver.getDriverName()));


       if(newDriver.isEmpty()) {
           throw new CabBookingException("No drivers found for this location");
       }

        Driver driver = newDriver.get(0);

        int distanceInKm = (int)Math.floor(cabbooking.getDistanceInKm());

       cabbooking.setFromDateTime(LocalDateTime.now());
       cabbooking.setToDateTime(LocalDateTime.now().plusMinutes(distanceInKm* 2L));



        cabbooking.setBill( driver.getCar().getPerKmRate()*cabbooking.getDistanceInKm());
        driver.setStatus(DriverStatus.Booked);
        driver.setNewLocation(cabbooking.getToLocation());
        cabbooking.setUser(user);
        cabbooking.setDriver(driver);
        cabbooking.setStatus(Status.Booked);

		return cabbookingrepo.save(cabbooking);
	}

	@Override
	public CabBooking updateCabBooking(Integer cabBookingId, CabBooking cabbooking) {
		if(cabbooking==null) {
            throw new CabBookingException("Cab object is null");
        }
		CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cab booking id"));

        Driver driver = c.getDriver();

        if(c.getStatus().equals(Status.Cancelled)) {
            throw new CabBookingException("cab is cannot be updated as it is already Cancelled");
        }


        int distanceInKm = (int)Math.floor(cabbooking.getDistanceInKm());

        driver.setNewLocation(cabbooking.getToLocation());
		c.setFromDateTime(LocalDateTime.now());
		c.setToDateTime(LocalDateTime.now().plusMinutes(distanceInKm* 2L));
		c.setToLocation(cabbooking.getToLocation());
		c.setDistanceInKm(cabbooking.getDistanceInKm());
		c.setBill( driver.getCar().getPerKmRate()*cabbooking.getDistanceInKm());
		return cabbookingrepo.save(c);
	}

	@Override
	public String cancelCabBooking(Integer cabBookingId) {
		CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cab id"));
		if(c.getStatus().equals(Status.Cancelled)) {
            throw new CabBookingException("cab is already Cancelled");
        }
        c.setStatus(Status.Cancelled);
        c.getDriver().setStatus(DriverStatus.Available);
        return "cab booking is cancelled";
	}

	@Override
	public List<CabBooking> viewAllTrips(String  email) {
		Users user=userrepo.findByEmail(email).orElseThrow(()-> new CabBookingException("enter valid userId"));
		List<CabBooking> list=user.getCabBookings();
		if(list.isEmpty()) throw new CabBookingException("user did not book any cab");
		return list;

	}

    @Override
    public CabBooking completeTrip(Integer cabBookingId, String email) {
        CabBooking c= cabbookingrepo.findById(cabBookingId).orElseThrow(()->new CabBookingException("enter valid cab id"));
        if(c.getStatus().equals(Status.Cancelled)) {
            throw new CabBookingException("cab is already Cancelled");
        }
        if(c.getStatus().equals(Status.COMPLETED)) {
            throw new CabBookingException("cab is already completed");
        }
        Users user=userrepo.findByEmail(email).orElseThrow(()-> new CabBookingException("enter valid userId"));
        Wallet wallet=user.getWallet();
        walletServices.payRideBill(wallet.getWalletId(),c.getBill());
//        wallet.setBalance(wallet.getBalance()-c.getBill());
//        user.setWallet(wallet);
        c.setStatus(Status.COMPLETED);
        c.getDriver().setStatus(DriverStatus.Available);
        return cabbookingrepo.save(c);
    }

    @Override
    public List<CabBooking> viewAllBookendCab() {
        List<CabBooking> list=cabbookingrepo.findAll();
        List<CabBooking> list1=new ArrayList<>();
        for(CabBooking c:list) {
            if(c.getStatus().equals(Status.Booked)) {
                list1.add(c);
            }
        }
        return list1;
    }


}
