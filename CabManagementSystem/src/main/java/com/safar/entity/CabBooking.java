package com.safar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CabBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabBookingId;

    @NotNull(message = "From location cannot be null")
    @Size(min = 4, max = 20, message = "From location must be between 4 and 20 characters")
    private String fromLocation;

    @NotNull(message = "To location cannot be null")
    @Size(min = 4, max = 20, message = "To location must be between 4 and 20 characters")
    private String toLocation;


    @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime fromDateTime;


    @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime toDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    //@DecimalMin("0.0")
    @NotNull(message = "Distance cannot be null")
    private Float distanceInKm;

    //@DecimalMin("0.0")
    @NotNull(message = "Bill cannot be null")
    private Float bill;
    
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    private Users user;
    
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Driver driver;

}
