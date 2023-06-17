package com.safar.service;

import com.safar.entity.Users;
import com.safar.exceptions.UsersException;

import java.util.List;

public interface UserService {

    public Users registerCustomer(Users customer);

    public Users getUserDetailsByEmail(String email)throws UsersException;

    public List<Users> getAllUsersDetails()throws UsersException;

    public Users updateUserDetails(Users users)throws UsersException;

    public Users deleteUserEmail(String email)throws UsersException;
    public List<Users> getAllUsersDetailsByRole(String role)throws UsersException;
}
