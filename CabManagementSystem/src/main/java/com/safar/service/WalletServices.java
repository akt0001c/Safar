package com.safar.service;

import java.util.List;

import com.safar.entity.Transactions;
import com.safar.entity.Wallet;



public interface WalletServices {
  public Wallet addMoney(Integer walletId,Float amount);
  public List<Transactions> getAllTranactions(Integer walletId);
  public Wallet payRideBill(Integer walletId,Float bill);
  public Wallet changeStatus(Integer walletId);
  public Wallet createWallet(String  email);
  public Wallet getWallet(Integer id);
}
