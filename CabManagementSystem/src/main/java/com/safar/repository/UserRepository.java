package com.safar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safar.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>,PagingAndSortingRepository<Users, Integer>{

}
