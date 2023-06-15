package com.safar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safar.entity.CabBooking;

@Repository
public interface CabBookingRepository extends JpaRepository<CabBooking,Integer>,PagingAndSortingRepository<CabBooking,Integer>{

}
