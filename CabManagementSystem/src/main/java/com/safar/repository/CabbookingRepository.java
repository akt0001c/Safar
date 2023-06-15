package com.safar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safar.entity.Cabbooking;

@Repository
public interface CabbookingRepository extends JpaRepository<Cabbooking,Integer>,PagingAndSortingRepository<Cabbooking,Integer>{

}
