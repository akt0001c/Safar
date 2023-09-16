package com.safar.controller;

import com.safar.entity.CabBooking;
import com.safar.entity.Users;
import com.safar.service.CabBookingService;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private CabBookingService cabBookingService;

    	/*

	  {
        "username": "aman",
        "password": "1234",
        "email": "aman@gmail.com",
        "phone": "1234567890",
        "address": "kanpur",
        "role": "admin"

    }

	 */

    /**
     * Test endpoint for verifying Spring Security configuration.
     *
     * @return A welcome message.
     */
    @GetMapping("/users/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }

    /**
     * Inserts a new cab booking for a user.
     *
     * @param cabBooking      The cab booking details to insert.
     * @param authentication  The authentication object for the logged-in user.
     * @return The inserted cab booking.
     */
    @PostMapping("/users/saveCavBooking")
    public ResponseEntity<CabBooking> saveCabBookingHandler(@RequestBody CabBooking cabBooking, Authentication authentication) {
        String email = authentication.getName();
        log.info(email + " user email");
        Users user = userService.getUserDetailsByEmail(email);
        CabBooking cabBooking1 = cabBookingService.insertCabBooking(cabBooking, user.getEmail());
        return new ResponseEntity<>(cabBooking1, HttpStatus.ACCEPTED);
    }

    /**
     * Inserts a new customer/user.
     *
     * @param user The user details to insert.
     * @return The inserted user.
     */
    @PostMapping("/users")
    public ResponseEntity<Users> saveCustomerHandler(@RequestBody Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole().toUpperCase());
        Users users = userService.registerCustomer(user);
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The user's details.
     */
    @GetMapping("/users/{email}")
    public ResponseEntity<Users> getUserByEmailHandler(@PathVariable("email") String email) {
        Users user = userService.getUserDetailsByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsersHandler() {
        List<Users> users = userService.getAllUsersDetails();
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    /**
     * Updates user details by their email.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user.
     */
    @PatchMapping("/users/{email}")
    public ResponseEntity<Users> updateUserDetailsByEmailHandler(@PathVariable("email") String email, @RequestBody Users users) {
        Users user = userService.updateUserDetailsByEmail(email, users);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    /**
     * Deletes a user by their email.
     *
     * @param email The email of the user to delete.
     * @return The deleted user.
     */
    @DeleteMapping("/users/{email}")
    public ResponseEntity<Users> deleteUserByEmailHandler(@PathVariable("email") String email) {
        Users user = userService.deleteUserEmail(email);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves a list of users by their role.
     *
     * @param role The role to filter users.
     * @return A list of users with the specified role.
     */
    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<Users>> getAllUsersDetailsByRoleHandler(@PathVariable("role") String role) {
        List<Users> users = userService.getAllUsersDetailsByRole(role);
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves details of the logged-in customer/user.
     *
     * @param auth The authentication object for the logged-in user.
     * @return The details of the logged-in user.
     */
    @GetMapping("/signIn")
    public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth) {
        log.info(auth.getName()); // This will print the email of the logged-in user
        Users users = userService.getUserDetailsByEmail(auth.getName());
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }
}
