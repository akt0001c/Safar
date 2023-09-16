package com.safar.service;

import com.safar.entity.CabBooking;

import java.util.List;

public interface CabBookingService {

    /**
     * Inserts a new cab booking for a user.
     *
     * @param cabbooking The cab booking details to insert.
     * @param email      The email of the user making the booking.
     * @return The inserted cab booking.
     */
    public CabBooking insertCabBooking(CabBooking cabbooking, String email);

    /**
     * Updates the details of a cab booking.
     *
     * @param cabBookingId The ID of the cab booking to update.
     * @param cabbooking   The updated cab booking details.
     * @return The updated cab booking.
     */
    public CabBooking updateCabBooking(Integer cabBookingId, CabBooking cabbooking);

    /**
     * Cancels a cab booking by its ID.
     *
     * @param cabBookingId The ID of the cab booking to cancel.
     * @return A message indicating the result of the cancellation.
     */
    public String cancelCabBooking(Integer cabBookingId);

    /**
     * Retrieves a list of all trips booked by a particular user.
     *
     * @param email The email of the user.
     * @return A list of cab bookings made by the user.
     */
    public List<CabBooking> viewAllTrips(String email);

    /**
     * Completes a cab trip and updates the status accordingly.
     *
     * @param cabBookingId The ID of the cab booking to complete.
     * @param email        The email of the user completing the trip.
     * @return The completed cab booking.
     */
    public CabBooking completeTrip(Integer cabBookingId, String email);

    /**
     * Retrieves a list of all booked cab trips.
     *
     * @return A list of cab bookings with the status "Booked."
     */
    public List<CabBooking> viewAllBookendCab();
}
