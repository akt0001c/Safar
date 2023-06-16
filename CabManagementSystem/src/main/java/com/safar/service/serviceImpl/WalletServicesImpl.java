package com.safar.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;


import com.safar.service.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;

import com.safar.entity.Wallet;
import com.safar.exceptions.TransactionFaliureException;
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
		 
		 if(ob.getStatus().equals(WalletStatus.Inactive))
			   throw new WalletException("Wallet is Inactive so please activate the wallet first");
		 
		 
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
	public List<Transactions> getAllTranactions(Integer walletId ) {
		if(walletId==null )
			   throw new WalletException("Invalid Details");
		 Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));
		 
		 if(ob.getStatus().equals(WalletStatus.Inactive))
			   throw new WalletException("Wallet is Inactive so please activate the wallet first");
			    
		
		List<Transactions>  list=  ob.getTransactions();
		
		
		return list;
	}

	@Override
	public Wallet payRideBill(Integer walletId, Float bill) {
		if(walletId==null || bill == null)
			   throw new WalletException("Invalid Details");
		
		Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));
		
		 if(ob.getStatus().equals(WalletStatus.Inactive))
			   throw new WalletException("Wallet is Inactive so please activate the wallet first");
		
		if(ob.getBalance()<bill)
		{
			float required= bill - ob.getBalance();
			throw new TransactionFaliureException("Wallet Balance is low please add "+required+" amount first into your wallet");
		}
		
		ob.setBalance(ob.getBalance()-bill);
		Transactions trans= new Transactions();
		trans.setTransactionDate(LocalDateTime.now());
		trans.setAmount(bill);
		trans.setType(TransactionType.Debit);
		trans.setCurrentBalance(ob.getBalance());
		
		ob.getTransactions().add(trans);
		
		Wallet res= wrepo.save(ob);
		return res;
		
		
		
		
	}

	@Override
	public Wallet changeStatus(Integer walletId) {
		if(walletId==null )
			   throw new WalletException("Invalid Details");
		
		 Wallet ob= wrepo.findById(walletId).orElseThrow(()->new WalletException("No Wallet Found"));
		 
		 if(ob.getStatus().equals(WalletStatus.Active))
			     ob.setStatus(WalletStatus.Inactive);
		 else
			 ob.setStatus(WalletStatus.Active);
		 
		 Wallet res= wrepo.save(ob);
		
		return res;
	}
	
	
	
   
}
