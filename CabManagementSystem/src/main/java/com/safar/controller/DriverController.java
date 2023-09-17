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
@RequestMapping("/ADMIN")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    /*

    {
        "driverName" : "amank",
        "email" : "amansingh@gmail.com",
        "password" : "aman123",
        "mobileNo" : "1234567880",
        "address" : "delhi",
        "licenceNo" : "1234561890",
        "rating" : 4.5,
        "newLocation" : "delhi",
        "status" : "Available",
        "car" : {
            "carType" : "SUV",
            "carNumber" : "UP 12 1234",
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

    /**
     * Inserts a new driver.
     *
     * @param driver The driver details to insert.
     * @return The inserted driver.
     */
    @PostMapping("/driver")
    public ResponseEntity<Driver> insertDriverHandler(@Valid @RequestBody Driver driver) {
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        Driver insertedDriver = driverService.insertDriver(driver);
        return new ResponseEntity<>(insertedDriver, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all drivers.
     *
     * @return A list of all drivers.
     */
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDriversHandler() {
        List<Driver> drivers = driverService.findAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    /**
     * Updates an existing driver's information.
     *
     * @param email  The email of the driver to update.
     * @param driver The updated driver details.
     * @return The updated driver.
     */
    @PatchMapping("/driver/{email}")
    public ResponseEntity<Driver> updateDriverHandler(@PathVariable String email, @RequestBody Driver driver) {
        Driver updatedDriver = driverService.updateDriver(email, driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }



    /*
    {
        "driverName" : "amank",
        "mobileNo" : "1234567880",
        "address" : "delhi",
        "rating" : 4.5,
        "newLocation" : "delhi",
        "status" : "Available",
        "car" : {
            "carType" : "SUV",
            "perKmRate" : 10.0
        }
    }
     */

    /**
     * Updates the name of a driver by their email.
     *
     * @param email The email of the driver to update.
     * @param name  The new name for the driver.
     * @return The updated driver.
     */
    @PatchMapping("/driver/{email}/{name}")
    public ResponseEntity<Driver> updateDriverHandler(@Valid @PathVariable String email, @PathVariable String name) {
        Driver updatedDriver = driverService.changeName(email, name);
        return new ResponseEntity<>(updatedDriver, HttpStatus.CREATED);
    }

    /**
     * Deletes a driver by their email.
     *
     * @param driverEmail The email of the driver to delete.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/driver/{driverEmail}")
    public ResponseEntity<String> deleteDriverHandler(@PathVariable String driverEmail) {
        String deletedDriver = driverService.deleteDriver(driverEmail);
        return new ResponseEntity<>(deletedDriver, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves a list of the best drivers.
     *
     * @return A list of the best drivers.
     */
    @GetMapping("/driver/bestdrivers")
    public ResponseEntity<List<Driver>> viewBestDriverListHandler() {
        List<Driver> viewBestDrivers = driverService.viewBestDrivers();
        return new ResponseEntity<>(viewBestDrivers, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves driver details by their email.
     *
     * @param driverEmail The email of the driver to retrieve.
     * @return The driver's details.
     */
    @GetMapping("/driver/{driverEmail}")
    public ResponseEntity<Driver> viewDriverByEmailHandler(@PathVariable String driverEmail) {
        Driver viewDriverById = driverService.getDriverDetailsByEmail(driverEmail);
        return new ResponseEntity<>(viewDriverById, HttpStatus.ACCEPTED);
    }
}
