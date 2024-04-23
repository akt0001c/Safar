# Safar

# This is an Online cab booking application called Safar, which provides REST API services for booking cabs and managing users with two roles: admin and user, through interactive API services.

![Home Page](https://github.com/akt0001c/fearful-doll-6867/assets/110126989/a5a4a01a-7dfa-4f08-8ad1-09217e88c833)

## Modules
 - User Module
 - Cab Managment Module
 - Driver Managment Module
 - Booking Managment Module
 - Wallet Managment Module

## Frontend And Backend Technologies
- HTML
- JavaScript
- CSS
- Spring Boot
- Maven
- Spring Data Jpa
- Hibernate
- Spring Security
- Mysql
- Lombok
- Swagger

 *Gihub Link-> [https://github.com/akt0001c/Safar.git](https://github.com/akt0001c/Safar.git)*
  
  *Netlify Link-> https://6498687fdd20971f58da64a1--statuesque-jalebi-8543ee.netlify.app/*

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database Configuration](#database-configuration)
- [Testing](#testing)
- [Contributing](#contributing)
- [Contributors](#Contributors)

## Features

- User registration and authentication.
- Booking cabs for rides.
- Driver registration and management.
- Wallet system for managing payments.
- Viewing ride history.
- Admin functionality for managing drivers and ride history.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- HTML, JavaScript, CSS knowledge.
- Spring Boot, Maven, Spring Data JPA, Hibernate, Spring Security, and MySQL.
- Lombok and Swagger for API documentation.







### ER DIGRAM
![SAFAR DIAGRAM](https://github.com/akt0001c/fearful-doll-6867/assets/115461689/59fbdd3b-f22e-41c7-87c2-faf1d330cf34)

## Getting Started

To get started, follow these steps:
1. Clone the project: `https://github.com/akt0001c/Safar.git`
2. Configure the database in `application.properties`.
3. Build and run the application.

## Usage

- Visit the API documentation at `http://localhost:8888/swagger-ui/` to explore available endpoints.
- Use the provided API endpoints for user registration, cab booking, driver management, and more.

## API Endpoints

### User Module

- `POST /users`: Register a new user.
- `POST /users/saveCabBooking`: Book a cab.
- `GET /users/{email}`: Retrieve user details by email.
- `GET /users`: Retrieve a list of all users.
- `PATCH /users/{email}`: Update user details by email.
- `DELETE /users/{email}`: Delete a user by email.
- `GET /users/role/{role}`: Retrieve users by role.
- `GET /users/hello`: Test endpoint for Spring Security.

### Cab Booking Module

- `POST /cabBooking`: Book a cab.
- `PATCH /cabBooking/{cabBookingId}`: Update a cab booking.
- `GET /cabBooking/cancel/{cabBookingId}`: Cancel a cab booking.
- `GET /cabBooking/history`: View booking history for a user.
- `GET /cabBooking/completeTrip/{cabBookingId}`: Complete a cab trip.
- `GET /ADMIN/cabBooking/history`: View booking history for all cab bookings.

### Driver Management Module

- `POST /ADMIN/driver`: Insert a new driver.
- `GET /ADMIN/drivers`: Retrieve a list of all drivers.
- `PATCH /ADMIN/driver/{email}`: Update driver details by email.
- `PATCH /ADMIN/driver/{email}/{name}`: Update driver's name by email.
- `DELETE /ADMIN/driver/{driverEmail}`: Delete a driver by email.
- `GET /ADMIN/driver/bestdrivers`: Retrieve a list of the best drivers.
- `GET /ADMIN/driver/{driverEmail}`: Retrieve driver details by email.

### Wallet Management Module

- `POST /WALLET/addMoney`: Add money to a user's wallet.
- `PATCH /WALLET/changeStatus`: Change the status of a wallet.
- `POST /WALLET/createWallet/{email}`: Create a wallet for a user.
- `GET /WALLET/getWallet`: Retrieve a user's wallet.
- `GET /WALLET/WalletDetails`: Retrieve the wallet details of the logged-in user.

## Database Configuration

Configure your database settings in `application.properties`:

```properties
server.port=8888

# Database specific properties
spring.datasource.url=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=
spring.datasource.password=

# ORM software specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Testing
- mvn test
- mvc test for controllers

## Contributors
- Ankit Choubry
- Aman Kumar
- Susheel Kumar
- Dhanushpriyan




## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push the changes to your fork.
5. Create a pull request explaining your changes.


## Contact

For any questions or suggestions, feel free to contact us at amankumar.ak0012@gmail.com or akt00071000@gmail.com .


