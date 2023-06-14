package com.safar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.safar.entity.Transactions;



@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>, JpaRepositoryImplementation<Transactions, Integer> {

}
