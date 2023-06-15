package com.safar.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.safar.entity.Wallet;
import com.safar.exceptions.WalletException;
import com.safar.repository.WalletRepository;
import com.safar.entity.*;

public class WalletServicesImpl implements WalletServices {
	
	private WalletRepository wrepo;
	
	
    @Autowired
	public void setWrepo(WalletRepository wrepo) {
		this.wrepo = wrepo;
	}

	@Override
	public Wallet addMoney(Integer walletId, Float amount) {
		if(walletId==null || amount == null)
			   throw new WalletException("Invalid Details");
		 Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));
		 
		 
		 ob.setBalance(ob.getBalance()+amount);
		 Transactions trans= new Transactions();
		 trans.setTransactionDate(LocalDateTime.now());
		 trans.setAmount(amount);
		 trans.setType(TransactionType.Credit);
		 trans.setCurrentBalance(ob.getBalance());
		 
		 ob.getTransactions().add(trans);
		 Wallet res= wrepo.save(ob);
		
		return res;
	}

	@Override
	public List<Transactions> getAllTranactions(Integer walletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Wallet payRideBill(Float bill) {
		// TODO Auto-generated method stub
		return null;
	}
   
}
