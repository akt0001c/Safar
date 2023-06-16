package com.safar.service.serviceImpl;

import com.safar.entity.Driver;
import com.safar.exceptions.DriverException;
import com.safar.repository.DriverRepository;
import com.safar.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;



    //    for inserting a new driver
    @Override
    public Driver insertDriver(Driver driver) throws DriverException {

        if(driver == null){
            throw new DriverException("Driver should not be null");
        }

//        inserting the driver
        return driverRepository.save(driver);

    }



    //    for updating a driver
    @Override
    public Driver updateDriver(Driver driver) throws DriverException {
        Optional<Driver> opt = driverRepository.findById(driver.getDriverId());

//        check for this id driver exist or not
        if(opt.isEmpty()) throw new DriverException("Driver should be present");
        Driver driver1 = opt.get();
        driver1.setDriverName(driver.getDriverName());
        driver1.setEmail(driver.getEmail());
        driver1.setAddress(driver.getAddress());
        driver1.setMobileNo(driver.getMobileNo());
        driver1.setLicenceNo(driver.getLicenceNo());
        driver1.setPassword(driver.getPassword());
        driver1.setRating(driver.getRating());
        driver1.setCar(driver.getCar());

//        now saving Driver in database
        return driverRepository.save(driver1);

    }




    //    delete a driver for given id
    @Override
    public String deleteDriver(int driverId) throws DriverException {
        Optional<Driver> opt = driverRepository.findById(driverId);

//        check this driver exist or not
        if(opt.isPresent()){
            driverRepository.deleteById(driverId);
            return "Driver deleted successfully";
        }else{
            throw new DriverException("Driver should be present for deleting");
        }

    }




    //    viewing the list of best drivers
    @Override
    public List<Driver> viewBestDrivers() {

//        find all driver from driver table
         List<Driver> drivers = driverRepository.findAll();

//         creating a new list for storing best drivers who have rating 4.5 plus
        List<Driver> bestDriverList = new ArrayList<>();

        for (Driver driver:drivers){
            if(driver.getRating() >= 4.5){
                bestDriverList.add(driver);
            }
        }
//        now we can return total best drivers who are present
        if(bestDriverList.size() == 0){
            throw new DriverException("There is no best driver at this time");
        }else{
            bestDriverList.sort(Comparator.comparing(Driver::getRating).reversed());
            return bestDriverList;
        }


    }





    //    view driver by his id
    @Override
    public Driver viewDriver(int driverId) throws DriverException {
        Optional<Driver> opt = driverRepository.findById(driverId);

//        check if driver is present or not with this perticular id;
        if(opt.isPresent()){
            Driver driver = opt.get();
            return driver;
        }else{
            throw new DriverException("Driver not found");
        }
    }

    @Override
    public Driver getDriverDetailsByEmail(String email) throws DriverException {
        return driverRepository.findByEmail(email).orElseThrow(()-> new DriverException("Driver not found with this email "+ email));
    }


}
