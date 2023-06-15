package com.safar.service;

import java.util.List;

import com.safar.entity.Transactions;

public interface TransactionServices {
   public List<Transactions> getAllTransationByWalledId(Integer walledId);
}
