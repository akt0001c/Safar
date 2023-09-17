
package com.safar.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.safar.entity.Users;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Users testUser;

    @BeforeEach
    public void setUp() {
        // Initialize a test user with valid data
        testUser = new Users();
        testUser.setUsername("Aman");
        testUser.setEmail("aman@gmail.com");
        testUser.setAddress("Kanpur");
        testUser.setPassword("12345"); // Do not encode the password here, as it should be done by the controller
        testUser.setPhone("9087654321");
        testUser.setRole("ADMIN");
    }

    @AfterEach
    public void tearDown() {
        // Clean up: Delete the test user after the test
        // Implement this part depending on how your application handles user deletion
    }

    @Test
    @DisplayName("Customer can be registered")
    public void testSaveCustomerHandler_whenValidDetailsProvided_returnSaveCustomer() throws Exception {
        // Arrange
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Users> entity = new HttpEntity<>(testUser, httpHeaders);

        // Act
        ResponseEntity<Users> re = testRestTemplate.postForEntity("/users", entity, Users.class);

        Users createdUser = re.getBody();

        // Assert
        assertEquals(HttpStatus.CREATED, re.getStatusCode(), "Created status code is not 201");
        assertNotNull(createdUser.getUserId(), "Registered user should have an id");
    }
}
