package com.safar.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    @NotNull(message = "Name should not be null")
    private String driverName;


    @NotBlank
    @Email(message = "Formate should be name@gamil.com")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty
    @Size(max = 13,min = 10)
    private String mobileNo;

    @NotEmpty
    private String address;

    @NotEmpty
    private String licenceNo;

    @NotEmpty(message = "Please provide rating for driver")
    private float rating;

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    private Car car;

}
