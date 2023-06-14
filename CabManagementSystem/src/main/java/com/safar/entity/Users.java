package com.safar.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Users {
    @Id
    private Integer userId;
    @NotNull(message = "User Name cannot be null")
    @Size(min = 3, max = 30, message = "User Name must be between 3 and 30 characters")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 4, max = 30, message = "Password must be between 4 and 30 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Size(min = 4, max = 25, message = "Email must be between 3 and 35 characters")
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
    private String email;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, max = 13, message = "Phone must be 10 to 12 characters")
    private String phone;

    @NotNull(message = "Address cannot be null")
    @Size(min = 4, max = 50, message = "Address must be between 4 and 50 characters")
    private String address;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CabBooking> cabBookings;

}
