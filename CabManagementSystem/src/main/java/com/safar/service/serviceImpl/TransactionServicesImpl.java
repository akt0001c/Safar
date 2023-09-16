package com.safar.service.serviceImpl;

import java.util.List;

import com.safar.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;

import com.safar.entity.Transactions;
import com.safar.entity.Wallet;
import com.safar.exceptions.TransactionsException;
import com.safar.exceptions.WalletException;
import com.safar.repository.TransactionRepository;
import com.safar.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
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



    /**
     * Retrieves a list of transactions by wallet ID.
     * If the wallet ID is null, it throws a WalletException indicating invalid wallet details.
     * Checks if a wallet with the given ID exists, throws an exception if not found.
     * Returns a list of transactions associated with the wallet.
     *
     * @param walletId The ID of the wallet to retrieve transactions for.
     * @return The list of transactions associated with the wallet.
     * @throws WalletException If the wallet is not found.
     * @throws TransactionsException If no transactions are found.
     */
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
