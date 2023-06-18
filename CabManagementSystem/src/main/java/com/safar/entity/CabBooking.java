package com.safar.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
