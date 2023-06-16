package com.safar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carId;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @NotNull(message = "Should not be null")
    private float perKmRate;

    @JsonIgnore
    @OneToOne
    private Driver driver;
}
