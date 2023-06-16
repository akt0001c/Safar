package com.safar.repository;

import com.safar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    public Optional<Users> findByEmail(String email);
}
