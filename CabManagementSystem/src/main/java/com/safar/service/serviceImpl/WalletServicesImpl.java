package com.safar.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;


import com.safar.service.WalletServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.safar.entity.Wallet;
import com.safar.exceptions.TransactionFaliureException;
import com.safar.exceptions.UsersException;
import com.safar.exceptions.WalletException;
import com.safar.repository.UserRepository;
import com.safar.repository.WalletRepository;
import com.safar.entity.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletServicesImpl implements WalletServices {


    @Autowired
	private WalletRepository wrepo;
    @Autowired
	private UserRepository urepo;



    /**
     * Adds money to the wallet with the specified walletId.
     * If the walletId or amount is null, it throws a WalletException indicating invalid details.
     * Checks if the wallet is active and adds the specified amount to the balance.
     * Creates a credit transaction record for the added amount.
     *
     * @param walletId The wallet ID to add money to.
     * @param amount   The amount to be added to the wallet.
     * @return The updated wallet details.
     */
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

    /**
     * Retrieves all transactions associated with the wallet with the specified walletId.
     * If the walletId is null, it throws a WalletException indicating invalid details.
     * Checks if the wallet is active before retrieving transactions.
     *
     * @param walletId The wallet ID to retrieve transactions for.
     * @return The list of transactions associated with the wallet.
     */
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


    /**
     * Pays a ride bill from the wallet with the specified walletId.
     * If the walletId or bill amount is null, it throws a WalletException indicating invalid details.
     * Checks if the wallet is active and if the balance is sufficient for the bill.
     * Deducts the bill amount from the wallet balance and creates a debit transaction record.
     *
     * @param walletId The wallet ID to pay the ride bill from.
     * @param bill     The ride bill amount to be paid.
     * @return The updated wallet details.
     */
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


    /**
     * Changes the status (Active/Inactive) of the wallet with the specified walletId.
     * If the walletId is null, it throws a WalletException indicating invalid details.
     * Toggles the status of the wallet and saves the changes.
     *
     * @param walletId The wallet ID to change the status of.
     * @return The updated wallet details.
     */
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


    /**
     * Creates a new wallet for the user with the specified email.
     * If the email is null, it throws a WalletException indicating invalid details.
     * Checks if the user already has an allocated wallet and prevents creating another one.
     * Creates a new wallet with an initial balance of 0.0f and an active status.
     *
     * @param email The email of the user for whom to create a wallet.
     * @return The created wallet details.
     */
	@Override
	public Wallet createWallet(String email) {
		if(email==null )
			   throw new WalletException("Invalid Details");
		
		Users user= urepo.findByEmail(email).orElseThrow(()->new UsersException("User does not exist") );
		log.info("user is "+user);

		Wallet ob2= user.getWallet();
		    if(ob2!=null)
		    	  throw new WalletException("Wallet has been already  allocated for this user So Another Wallet cannot be created ");
		
		Wallet ob= new Wallet();
		ob.setBalance(0.0f);
		ob.setStatus(WalletStatus.Active);
		ob.setUser(user);
		
		Wallet res= wrepo.save(ob);
		
		return res;
	}



    /**
     * Retrieves the wallet details by wallet ID.
     * If the wallet ID is null or the wallet is not found, it throws a WalletException.
     *
     * @param id The wallet ID to retrieve.
     * @return The wallet details.
     */
	@Override
  public Wallet  getWallet(Integer id){
      Wallet res= wrepo.findById(id).orElseThrow(()->new WalletException("Wallet not found"));
	  return res;
  }


    /**
     * Retrieves the wallet details of the logged-in user by email.
     * If the email is null or the user's wallet is not found, it throws exceptions.
     *
     * @param email The email of the logged-in user.
     * @return The wallet details of the logged-in user.
     */
	@Override
	public Wallet getLoggedUserWallet(String email) {
		if(email==null)
			  throw new UsersException("Invalid user details provided");
		
	   Users user= urepo.findByEmail(email).orElseThrow(()->new UsersException(" User not found OR You should logged in"));
	   Wallet res= user.getWallet();
	   if(res==null)
		     throw new WalletException("NO Wallet found ");
		return res;
	}
	
   
}
