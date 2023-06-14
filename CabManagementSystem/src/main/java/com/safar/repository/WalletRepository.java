package com.safar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.safar.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>, JpaRepositoryImplementation<Wallet, Integer> {

}
