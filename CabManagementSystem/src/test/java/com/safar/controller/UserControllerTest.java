package com.safar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safar.entity.Users;
import com.safar.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private Users usersRequest;
    private Users usersResponse;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    public void init(){
    	 usersRequest = new Users();
         usersRequest.setUsername("Susheel");
         usersRequest.setEmail("sushil@gmail.com");
         Mockito.when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
         usersRequest.setPassword(passwordEncoder.encode("12345"));
         usersRequest.setPhone("9876543210");
         usersRequest.setAddress("Hamirpur");
         usersRequest.setRole("ADMIN");

         usersResponse = new Users();
         usersResponse.setUserId(10);
         usersResponse.setUsername("Susheel");
         usersResponse.setEmail("sushil@gmail.com");
         usersResponse.setPassword("12345");
         usersResponse.setPhone("9876543210");
         usersResponse.setAddress("Hamirpur");
         usersResponse.setRole("ADMIN");
    }

    @Test
    @DisplayName("Customer can be registered")
    public void testSaveCustomerHandler_whenValidDetailsProvided_returnRegisteredCustomer() throws Exception{
//        Arrange
        Mockito.when(userService.registerCustomer(any(Users.class))).thenReturn(usersResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(usersRequest));


//        act

      MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
      String responseBodyAsString = mvcResult.getResponse().getContentAsString();
      Users createdUser = new ObjectMapper().readValue(responseBodyAsString,Users.class);

//      assert
        assertEquals(usersResponse.getUsername(),createdUser.getUsername(),"Created user name is incorrect");
        assertNotNull(createdUser.getUserId(),"created user id should not be empty");
    }
}
