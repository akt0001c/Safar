package com.safar.controller;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.service.CabBookingService;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CabBookingController {

    /*

    {
          "fromLocation": "delhi",
          "toLocation": "pune",
          "distanceInKm": 15.0,
          "bill": 500.0

       }
     */

    @Autowired
    private CabBookingService cabBookingService;

    @Autowired
    private UserService userService;

    /**
     * Handles the creation of a new cab booking.
     *
     * @param cabBooking      The cab booking details to insert.
     * @param authentication  Authentication object containing user information.
     * @return The inserted cab booking.
     */
    @PostMapping("/cabBooking")
    public ResponseEntity<CabBooking> saveCabBookingHandler(@RequestBody CabBooking cabBooking, Authentication authentication) {

        String email = authentication.getName();
        log.info(email + " user email");

        Users user = userService.getUserDetailsByEmail(email);
        CabBooking cabBooking1 = cabBookingService.insertCabBooking(cabBooking, user.getEmail());
        return ResponseEntity.ok(cabBooking1);
    }

    /**
     * Handles the update of an existing cab booking.
     *
     * @param cabBooking     The updated cab booking details.
     * @param cabBookingId   The ID of the cab booking to update.
     * @return The updated cab booking.
     */
    @PatchMapping("/cabBooking/{cabBookingId}")
    public ResponseEntity<CabBooking> updateCabBookingHandler(@RequestBody CabBooking cabBooking, @PathVariable Integer cabBookingId) {
        CabBooking cabBooking1 = cabBookingService.updateCabBooking(cabBookingId, cabBooking);
        return ResponseEntity.ok(cabBooking1);
    }

    /**
     * Handles the cancellation of a cab booking.
     *
     * @param cabBookingId The ID of the cab booking to cancel.
     * @return A message indicating the result of the cancellation.
     */
    @GetMapping("/cabBooking/cancel/{cabBookingId}")
    public ResponseEntity<String> cancelCabBookingHandler(@PathVariable Integer cabBookingId) {
        String message = cabBookingService.cancelCabBooking(cabBookingId);
        return ResponseEntity.ok(message);
    }

    /**
     * Handles the retrieval of the booking history for a user.
     *
     * @param authentication Authentication object containing user information.
     * @return A list of cab bookings representing the user's trips.
     */
    @GetMapping("/cabBooking/history")
    public ResponseEntity<List<CabBooking>> getAllCabBookingHandler(Authentication authentication) {
        String email = authentication.getName();
        log.info(email + " user email");
        List<CabBooking> cabBookings = cabBookingService.viewAllTrips(email);
        return ResponseEntity.ok(cabBookings);
    }

    /**
     * Handles the completion of a cab trip.
     *
     * @param cabBookingId   The ID of the cab booking to mark as completed.
     * @param authentication Authentication object containing user information.
     * @return The completed cab booking.
     */
    @GetMapping("/cabBooking/completeTrip/{cabBookingId}")
    public ResponseEntity<CabBooking> completeTripHandler(@PathVariable Integer cabBookingId, Authentication authentication) {
        Users user = userService.getUserDetailsByEmail(authentication.getName());
        CabBooking cabBooking = cabBookingService.completeTrip(cabBookingId, user.getEmail());
        return ResponseEntity.ok(cabBooking);
    }

    /**
     * Handles the retrieval of the booking history for all cab bookings.
     *
     * @return A list of cab bookings representing all booked cab trips.
     */
    @GetMapping("/ADMIN/cabBooking/history")
    public ResponseEntity<List<CabBooking>> getAllCabBookingHandler() {
        List<CabBooking> cabBookings = cabBookingService.viewAllBookendCab();
        return ResponseEntity.ok(cabBookings);
    }
}
