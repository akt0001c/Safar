package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.masai.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>, JpaRepositoryImplementation<Wallet, Integer> {

}
