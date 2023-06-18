package com.safar.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @NotNull(message = "User Name cannot be null")
    @Size(min = 3, max = 30, message = "User Name must be between 3 and 30 characters")
    private String username;
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email( message = "Email is not valid")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, max = 13, message = "Phone must be 10 to 12 characters")
    @Column(unique = true)
    private String phone;

    @NotNull(message = "Address cannot be null")
    @Size(min = 4, max = 50, message = "Address must be between 4 and 50 characters")
    private String address;
    private String role;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CabBooking> cabBookings;
    
    @JsonIgnore
    @OneToOne(mappedBy="user" ,cascade=CascadeType.ALL)
    private Wallet wallet;

}
