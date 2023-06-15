package com.safar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.safar.entity.Transactions;
import com.safar.entity.Wallet;
import com.safar.exceptions.TransactionsException;
import com.safar.exceptions.WalletException;
import com.safar.repository.TransactionRepository;
import com.safar.repository.WalletRepository;

public class TransactionServicesImpl implements TransactionServices {
	
	private TransactionRepository trepo;
	private WalletRepository wrepo;
	
	
    @Autowired
	public void setTrepo(TransactionRepository trepo) {
		this.trepo = trepo;
	}


    @Autowired
	public void setWrepo(WalletRepository wrepo) {
		this.wrepo = wrepo;
	}



	@Override
	public List<Transactions> getAllTransationByWalledId(Integer walletId) {
		if(walletId==null)
			 throw new WalletException("Invalid Wallet details");
		
		Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("Wallet not exist"));
		
//		List<Transactions> list= trepo.findAllTransactionByWalletId(walletId);
        List<Transactions> list= trepo.findAll();
		 if(list.isEmpty())
			   throw new TransactionsException("NO TRANSACTION FOUND");

		return list;
	}

}
