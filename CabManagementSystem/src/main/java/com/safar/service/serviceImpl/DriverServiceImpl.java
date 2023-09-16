package com.safar.service.serviceImpl;

import com.safar.entity.Car;
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



    /**
     * Inserts a new driver into the system.
     * Validates the input driver object and associates it with a car.
     *
     * @param driver The driver to be inserted.
     * @return The inserted driver.
     * @throws DriverException If validation or insertion fails.
     */
    @Override
    public Driver insertDriver(Driver driver) throws DriverException {

        if(driver == null){
            throw new DriverException("Driver should not be null");
        }
        Car car = driver.getCar();
        car.setDriver(driver);

//        inserting the driver
        return driverRepository.save(driver);

    }



    /**
     * Retrieves a list of all drivers in the system.
     *
     * @return A list of all drivers.
     * @throws DriverException If no drivers found.
     */
    @Override
    public List<Driver> findAllDrivers() throws DriverException {
        List<Driver> drivers = driverRepository.findAll();
        if(drivers.isEmpty()) throw new DriverException("No Drivers found");
        return drivers;
    }


    /**
     * Updates a driver's details by their email.
     * Modifies the driver's name, location, and mobile number.
     *
     * @param email The email of the driver to update.
     * @param driver The updated driver details.
     * @return The updated driver.
     * @throws DriverException If the driver is not found.
     */
    @Override
    public Driver updateDriver(String email,Driver driver) throws DriverException {
        Optional<Driver> opt = driverRepository.findByEmail(email);

//        check for this id driver exist or not
        if(opt.isEmpty()) throw new DriverException("Driver should be present");
        Driver driver1 = opt.get();
        driver1.setDriverName(driver.getDriverName());
        driver1.setNewLocation(driver.getNewLocation());
        driver1.setMobileNo(driver.getMobileNo());

//        now saving Driver in database
        return driverRepository.save(driver1);

    }

    /**
     * Changes the name of a driver identified by their email.
     * If the driver is found, updates the driver's name and saves the changes.
     *
     * @param email The email of the driver to update.
     * @param name The new name for the driver.
     * @return The updated driver with the new name.
     * @throws DriverException If the driver is not found.
     */
    @Override
    public Driver changeName(String email, String name) throws DriverException {
        Optional<Driver> opt = driverRepository.findByEmail(email);

//        check this driver exist or not
        if(opt.isPresent()){
            Driver driver = opt.get();
            driver.setDriverName(name);
            driverRepository.save(driver);
            return driver;
        }else{
            throw new DriverException("Driver should be present for updating");
        }
    }


    /**
     * Deletes a driver by their email.
     * If the driver is found, deletes the driver from the database.
     *
     * @param email The email of the driver to delete.
     * @return A message indicating the driver was deleted successfully.
     * @throws DriverException If the driver is not found.
     */
    @Override
    public String deleteDriver(String email) throws DriverException {
        Optional<Driver> opt = driverRepository.findByEmail(email);

//        check this driver exist or not
        if(opt.isPresent()){
            Driver driver = opt.get();
            driverRepository.deleteById(driver.getDriverId());
            return "Driver deleted successfully";
        }else{
            throw new DriverException("Driver should be present for deleting");
        }

    }




    /**
     * Retrieves a list of best drivers with a rating of 4.5 or higher.
     *
     * @return A list of the best drivers.
     * @throws DriverException If no best drivers are found.
     */
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





    /**
     * Retrieves driver details by their ID.
     *
     * @param driverId The ID of the driver to retrieve.
     * @return The driver details.
     * @throws DriverException If the driver is not found.
     */
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


    /**
     * Retrieves driver details by their email.
     *
     * @param email The email of the driver to retrieve.
     * @return The driver details.
     * @throws DriverException If the driver is not found.
     */
    @Override
    public Driver getDriverDetailsByEmail(String email) throws DriverException {
        return driverRepository.findByEmail(email).orElseThrow(()-> new DriverException("Driver not found with this email "+ email));
    }


}
