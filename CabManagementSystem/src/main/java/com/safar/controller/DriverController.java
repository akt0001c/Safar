package com.safar.controller;


import com.safar.entity.Driver;
import com.safar.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    /*

    {
        "driverName" : "aman",
        "email" : "aman@gmail.com",
        "password" : "aman123",
        "mobileNo" : "1234567890",
        "address" : "delhi",
        "licenceNo" : "1234567890",
        "rating" : 4.5,
        "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0
        }
    }

    "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0,
            "carNumber" : "1234567890",
            "carType" : "sedan",
            "carDescription" : "good"
        }


     */

    @PostMapping("/driver")
    public ResponseEntity<Driver> insertDriverHandler(@Valid @RequestBody Driver driver){
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        Driver insertedDriver = driverService.insertDriver(driver);
        return new ResponseEntity<>(insertedDriver, HttpStatus.CREATED);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDriversHandler(){
        List<Driver> drivers = driverService.findAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.CREATED);
    }


    @GetMapping("/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    @PutMapping("/driver")
    public ResponseEntity<Driver> updateDriverHandler(@Valid @RequestBody Driver driver){
        Driver updatedDriver = driverService.updateDriver(driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/{name}")
    public ResponseEntity<Driver> updateDriverHandler(@Valid @PathVariable Integer id,@PathVariable String name){
        Driver updatedDriver = driverService.changeName(id,name);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }


    @DeleteMapping("/{driverId}")
    public ResponseEntity<String> deleteDriverHandler(@PathVariable Integer driverId){
        String deletedDriver = driverService.deleteDriver(driverId);
        return new ResponseEntity<>(deletedDriver, HttpStatus.ACCEPTED);
    }


    @GetMapping("/bestdrivers")
    public ResponseEntity<List<Driver>> viewBestDriverListHandler(){
        List<Driver> viewBestDrivers = driverService.viewBestDrivers();
        return new ResponseEntity<>(viewBestDrivers, HttpStatus.ACCEPTED);
    }



    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> viewDriverHandler(@PathVariable Integer driverId){
        Driver viewDriverById = driverService.viewDriver(driverId);
        return new ResponseEntity<>(viewDriverById, HttpStatus.ACCEPTED);
    }


//    @GetMapping("/signIn")
//    public ResponseEntity<String> getLoggedInDriverDetailsHandler(Authentication auth){
//        System.out.println(auth); // this Authentication object having Principle object details
//        Driver driver= driverService.getDriverDetailsByEmail(auth.getName());
//        return new ResponseEntity<>(driver.getDriverName()+"Logged In Successfully", HttpStatus.ACCEPTED);
//    }
}
