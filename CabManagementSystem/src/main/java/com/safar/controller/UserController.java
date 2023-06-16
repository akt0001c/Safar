package com.safar.controller;

import com.safar.entity.Users;
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


        List<Users> users= userService.getAllCustomerDetails();

        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);

    }

    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){


        log.info(auth.getName()); // this will print the email of the logged in user

        Users users= userService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(users.getUsername()+"Logged In Successfully", HttpStatus.ACCEPTED);
    }

}
