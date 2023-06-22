package com.safar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer driverId;

    @NotNull(message = "Name should not be null")
    private String driverName;


    @NotBlank
    @Email(message = "Format should be name@gamil.com")
    @Column(unique=true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    @Size(max = 13,min = 10)
    @Column(unique = true)
    private String mobileNo;

//    @NotEmpty
    private String address;

//    @NotEmpty
    @Column(unique = true)
    private String licenceNo;

//    @NotEmpty(message = "Please provide rating for driver")
    private float rating;

    private String newLocation;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Car car;
   
    @JsonIgnore
    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<CabBooking> cabBookings = new ArrayList<>();

}
