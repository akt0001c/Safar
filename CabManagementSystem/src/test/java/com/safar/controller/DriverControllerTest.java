package com.safar.controller;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safar.entity.Driver;
import com.safar.service.DriverService;

@WebMvcTest(controllers = DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DriverControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService userService;
    private Driver usersRequest;
    private Driver usersResponse;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    public void init(){
        usersRequest = new Driver();
        usersRequest.setDriverName("Aman");
        usersRequest.setEmail("aman@gmail.com");
        Mockito.when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        usersRequest.setPassword(passwordEncoder.encode("12345"));
        usersRequest.setMobileNo("7007219431");
        usersRequest.setAddress("Kanpur");
        usersRequest.setLicenceNo("LP123456");
        usersRequest.setNewLocation("Delhi");

        usersResponse = new Driver();
        usersResponse.setDriverId(10);
        usersResponse.setDriverName("Aman");
        usersResponse.setEmail("aman@gmail.com");
        usersResponse.setPassword("12345");
        usersResponse.setMobileNo("7007219431");
        usersResponse.setAddress("Kanpur");
        usersResponse.setLicenceNo("LP123456");
        usersResponse.setNewLocation("Delhi");
    }

    @Test
    @DisplayName("Customer can be registered")
    public void testInsertDriverHandler_whenValidDetailsProvided_returnInsertedDriver() throws Exception{
        Mockito.when(userService.insertDriver(any(Driver.class))).thenReturn(usersResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/driver")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(usersRequest));




        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        Driver createdUser = new ObjectMapper().readValue(responseBodyAsString,Driver.class);


        assertEquals(createdUser.getDriverName(),usersResponse.getDriverName(),"Created Driver name is incorrect");
        assertNotNull(createdUser.getDriverId(),"created driver id should not be empty");
    }
}