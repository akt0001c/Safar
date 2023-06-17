package com.safar.service;

import com.safar.entity.Driver;
import com.safar.exceptions.DriverException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface DriverService {


//    for inserting a new driver
    public Driver insertDriver(Driver driver) throws DriverException;


//    for getting all drivers
    public List<Driver> findAllDrivers() throws DriverException;


//    for updating a driver
    public Driver updateDriver(Driver driver) throws DriverException;

// for changing name of a driver
    public Driver changeName(Integer id,String name) throws DriverException;

//    delete a driver for given id
    public String deleteDriver(int driverId) throws DriverException;



//    viewing the list of best drivers
    public List<Driver> viewBestDrivers();


//    view driver by his id
    public Driver viewDriver(int driverId) throws DriverException;


    public Driver getDriverDetailsByEmail(String email) throws DriverException;
}
