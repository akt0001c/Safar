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

    @NotNull(message = "Car name should not be null")
    @NotBlank(message = "Car name should not be blank")
    private String carName;

    @NotNull(message = "Car Number should not be null")
    @NotBlank(message = "Car Number should not be blank")
    private String carNumber;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @NotNull(message = "Should not be null")
    private float perKmRate;

    @JsonIgnore
    @OneToOne
    private Driver driver;
}
