package com.safar.service;

import com.safar.entity.Driver;
import com.safar.exceptions.DriverException;

import java.util.List;

public interface DriverService {

    /**
     * Inserts a new driver into the system.
     *
     * @param driver The driver object to insert.
     * @return The inserted driver.
     * @throws DriverException If there's an issue with driver data or insertion.
     */
    public Driver insertDriver(Driver driver) throws DriverException;

    /**
     * Retrieves a list of all drivers.
     *
     * @return A list of all drivers.
     * @throws DriverException If there are no drivers found or other issues.
     */
    public List<Driver> findAllDrivers() throws DriverException;

    /**
     * Updates a driver's information by their email.
     *
     * @param email  The email of the driver to update.
     * @param driver The updated driver information.
     * @return The updated driver.
     * @throws DriverException If there are issues with the driver data or update process.
     */
    public Driver updateDriver(String email, Driver driver) throws DriverException;

    /**
     * Changes the name of a driver by their email.
     *
     * @param email The email of the driver to update.
     * @param name  The new name for the driver.
     * @return The updated driver.
     * @throws DriverException If there are issues with the driver data or update process.
     */
    public Driver changeName(String email, String name) throws DriverException;

    /**
     * Deletes a driver by their email.
     *
     * @param email The email of the driver to delete.
     * @return A message indicating the result of the deletion.
     * @throws DriverException If there are issues with the driver data or deletion process.
     */
    public String deleteDriver(String email) throws DriverException;

    /**
     * Retrieves a list of the best drivers based on certain criteria.
     *
     * @return A list of the best drivers.
     */
    public List<Driver> viewBestDrivers();

    /**
     * Retrieves driver information by their ID.
     *
     * @param driverId The ID of the driver to retrieve.
     * @return The driver with the specified ID.
     * @throws DriverException If the driver is not found.
     */
    public Driver viewDriver(int driverId) throws DriverException;

    /**
     * Retrieves driver information by their email.
     *
     * @param email The email of the driver to retrieve.
     * @return The driver with the specified email.
     * @throws DriverException If the driver is not found.
     */
    public Driver getDriverDetailsByEmail(String email) throws DriverException;
}
