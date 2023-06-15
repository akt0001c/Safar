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
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/driver")
    public ResponseEntity<Driver> insertDriverHandler(@Valid @RequestBody Driver driver){
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        Driver insertedDriver = driverService.insertDriver(driver);
        return new ResponseEntity<>(insertedDriver, HttpStatus.CREATED);
    }


    @GetMapping("/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    @PutMapping("/drivers/driver")
    public ResponseEntity<Driver> updateDriverHandler(@Valid @RequestBody Driver driver){
        Driver updatedDriver = driverService.updateDriver(driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }


    @DeleteMapping("/driver/{driverId}")
    public ResponseEntity<String> deleteDriverHandler(@PathVariable Integer driverId){
        String deletedDriver = driverService.deleteDriver(driverId);
        return new ResponseEntity<>(deletedDriver, HttpStatus.ACCEPTED);
    }


    @GetMapping("/drivers/bestdrivers")
    public ResponseEntity<List<Driver>> viewBestDriverListHandler(){
        List<Driver> viewBestDrivers = driverService.viewBestDrivers();
        return new ResponseEntity<>(viewBestDrivers, HttpStatus.ACCEPTED);
    }



    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<Driver> viewDriverHandler(@PathVariable Integer driverId){
        Driver viewDriverById = driverService.viewDriver(driverId);
        return new ResponseEntity<>(viewDriverById, HttpStatus.ACCEPTED);
    }


    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInDriverDetailsHandler(Authentication auth){
        System.out.println(auth); // this Authentication object having Principle object details
        Driver driver= driverService.getDriverDetailsByEmail(auth.getName());
        return new ResponseEntity<>(driver.getDriverName()+"Logged In Successfully", HttpStatus.ACCEPTED);
    }
}
