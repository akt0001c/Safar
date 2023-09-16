package com.safar.service;

import com.safar.entity.Users;
import com.safar.entity.Wallet;
import com.safar.exceptions.UsersException;

import java.util.List;

public interface UserService {

    /**
     * Registers a new customer.
     *
     * @param customer The customer object to be registered.
     * @return The registered user details.
     */
    public Users registerCustomer(Users customer);


    /**
     * Retrieves user details by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user details.
     * @throws UsersException If the user is not found.
     */
    public Users getUserDetailsByEmail(String email)throws UsersException;


    /**
     * Retrieves details of all users.
     *
     * @return A list of all user details.
     * @throws UsersException If no users are found.
     */
    public List<Users> getAllUsersDetails()throws UsersException;


    /**
     * Updates user details by email.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user details.
     * @throws UsersException If the user is not found.
     */
    public Users updateUserDetailsByEmail(String  email, Users users)throws UsersException;



    /**
     * Deletes a user by email.
     *
     * @param email The email of the user to delete.
     * @return The deleted user details.
     * @throws UsersException If the user is not found.
     */
    public Users deleteUserEmail(String email)throws UsersException;


    /**
     * Retrieves details of all users with the specified role.
     *
     * @param role The role for which to retrieve user details.
     * @return A list of user details with the specified role.
     * @throws UsersException If no users with the specified role are found.
     */
    public List<Users> getAllUsersDetailsByRole(String role)throws UsersException;
}
