package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaRepositoryImplementation<Transaction, Integer> {

}
