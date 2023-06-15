package com.services;

import java.util.List;

import com.masai.entity.Transactions;
import com.masai.entity.Wallet;

public interface WalletServices {
  public Wallet addMoney(Integer walletId,Float amount);
  public List<Transactions> getAllTranactions(Integer walletId);
  public Wallet payRideBill(Float bill);
}
