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

    @GetMapping("/users/hello")
    public String testHandler() {
        return "Welcome to Spring Security";
    }


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

    @PostMapping("/users/saveCavBooking")
    public ResponseEntity<CabBooking> saveCabBookingHandler(@RequestBody CabBooking cabBooking,  Authentication authentication){

        String email=authentication.getName();
        log.info(email+"user email");

        Users user= userService.getUserDetailsByEmail(email);

//        log.info(user.getUsername());
//        cabBooking.setUser(user);

        CabBooking cabBooking1= cabBookingService.insertCabBooking(cabBooking,user.getEmail());

        return new ResponseEntity<>(cabBooking1, HttpStatus.ACCEPTED);

    }

    @PostMapping("/users")
    public ResponseEntity<Users> saveCustomerHandler(@RequestBody Users user){


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("ROLE_"+user.getRole().toUpperCase());


        Users users= userService.registerCustomer(user);

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);

    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Users> getUserByEmailHandler(@PathVariable("email") String email){


        Users user= userService.getUserDetailsByEmail(email);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsersHandler(){


        List<Users> users= userService.getAllUsersDetails();

        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);

    }


    @PatchMapping("/users/{email}")
    public ResponseEntity<Users> updateUserDetailsByEmailHandler(@PathVariable("email") String email, @RequestBody Users users){

        Users user= userService.updateUserDetailsByEmail(email,users);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<Users> deleteUserByEmailHandler(@PathVariable("email") String email){

        Users user= userService.deleteUserEmail(email);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<Users>> getAllUsersDetailsByRoleHandler(@PathVariable("role") String role){

        List<Users> users= userService.getAllUsersDetailsByRole(role);

        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){


        log.info(auth.getName()); // this will print the email of the logged in user

        Users users= userService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(users.getUsername()+" Logged In Successfully: ", HttpStatus.ACCEPTED);
    }

}
