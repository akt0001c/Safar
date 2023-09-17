package com.safar.service.serviceImpl;

import com.safar.entity.Users;
import com.safar.entity.Wallet;
import com.safar.entity.WalletStatus;
import com.safar.exceptions.UsersException;
import com.safar.repository.UserRepository;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * Registers a new customer by creating a new user with a zero-balance wallet.
     * If the users object is null, it throws a UsersException indicating invalid user details.
     * Creates a new wallet with an initial balance of 0.0f and associates it with the user.
     *
     * @param users The user to be registered.
     * @return The registered user with an associated wallet.
     */
    @Override
    public Users registerCustomer(Users users) {
        if (users == null)
            throw new UsersException("Invalid users Details");
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0f);
        wallet.setStatus(WalletStatus.Active);
        wallet.setUser(users);
        users.setWallet(wallet);
//        wallet.setWalletId(users.getUserId());
        return userRepository.save(users);
    }


    /**
     * Retrieves user details by email.
     * Throws UsersException if no user is found with the given email.
     *
     * @param email The email of the user to retrieve.
     * @return The user details.
     * @throws UsersException If the user is not found.
     */
    @Override
    public Users getUserDetailsByEmail(String email) throws UsersException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
    }




    /**
     * Retrieves a list of all users' details.
     * Throws UsersException if no users are found.
     *
     * @return The list of all users' details.
     * @throws UsersException If no users are found.
     */
    @Override
    public List<Users> getAllUsersDetails() throws UsersException {

        List<Users> usersList = userRepository.findAll();

        if (usersList.isEmpty())
            throw new UsersException("No Users find");

        return usersList;
    }


    /**
     * Updates user details by email.
     * Throws UsersException if no user is found with the given email.
     * Modifies the username, phone, and address of the user and saves the changes.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user details.
     * @throws UsersException If the user is not found.
     */
    @Override
    public Users updateUserDetailsByEmail(String  email, Users users) throws UsersException {
        Users users1 = userRepository.findByEmail(email).orElseThrow(() -> new UsersException("Users Not found with Id: " + users.getUserId()));
        users1.setUsername(users.getUsername());
        users1.setPhone(users.getPhone());
        users1.setAddress(users.getAddress());
        return userRepository.save(users1);
    }


    /**
     * Deletes a user by email.
     * Throws UsersException if no user is found with the given email.
     * Logs the deleted user information before deleting.
     *
     * @param email The email of the user to delete.
     * @return The deleted user.
     * @throws UsersException If the user is not found.
     */
    @Override
    public Users deleteUserEmail(String email) throws UsersException {
        Users users = userRepository.findByEmail(email).orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
        log.info(users.toString());
        userRepository.delete(users);
        return users;

    }


    /**
     * Retrieves a list of all users' details by their role.
     * Throws UsersException if no users with the specified role are found.
     *
     * @param role The role for which to retrieve users.
     * @return The list of users' details with the specified role.
     * @throws UsersException If no users with the role are found.
     */
    @Override
    public List<Users> getAllUsersDetailsByRole(String role) throws UsersException {

        List<Users> usersList = userRepository.findAllByRole("ROLE_"+role.toUpperCase());

        if (usersList.isEmpty())
            throw new UsersException("No Users find");

        return usersList;
    }




}
