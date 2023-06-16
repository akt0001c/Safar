package com.safar.service;

import com.safar.entity.Users;
import com.safar.exceptions.UsersException;

import java.util.List;

public interface UserService {

    public Users registerCustomer(Users customer);

    public Users getUserDetailsByEmail(String email)throws UsersException;

    public List<Users> getAllCustomerDetails()throws UsersException;
}
