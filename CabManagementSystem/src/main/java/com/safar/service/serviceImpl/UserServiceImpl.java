package com.safar.service.serviceImpl;

import com.safar.entity.Users;
import com.safar.exceptions.UsersException;
import com.safar.repository.UserRepository;
import com.safar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users registerCustomer(Users users) {
        if (users == null)
            throw new UsersException("Invalid users Details");
        return userRepository.save(users);
    }

    @Override
    public Users getUserDetailsByEmail(String email) throws UsersException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
    }

    @Override
    public List<Users> getAllUsersDetails() throws UsersException {

        List<Users> usersList = userRepository.findAll();

        if (usersList.isEmpty())
            throw new UsersException("No Users find");

        return usersList;
    }
}
