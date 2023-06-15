package com.safar.security;

import com.safar.entity.Driver;
import com.safar.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class DriverUserDetailsService implements UserDetailsService {

    @Autowired
    private DriverRepository driverRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Driver> opt = driverRepository.findByEmail(username);

        if(opt.isPresent()) {
            Driver driver = opt.get();
//Empty Authorities
            List<GrantedAuthority> authorities= new ArrayList<>();
//authorities.add(new SimpleGrantedAuthority(customer.getRole()));
            return new User(driver.getEmail(), driver.getPassword(), authorities);
//return new CustomerUserDetails(customer);
        }else
            throw new BadCredentialsException("User Details not found with this username: "+username);
    }
}
