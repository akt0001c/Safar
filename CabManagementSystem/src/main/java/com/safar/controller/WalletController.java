package com.safar.controller;

import com.safar.entity.Users;
import com.safar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.safar.entity.Wallet;
import com.safar.exceptions.WalletException;
import com.safar.service.WalletServices;

@RestController
@RequestMapping("/WALLET")
public class WalletController {

    @Autowired
	private WalletServices wService;
    @Autowired
    private UserService userService;
    
	@PostMapping("/addMoney")
	public ResponseEntity<Wallet>addMoneyToWallet(Authentication auth, @RequestParam("amount") Float amount){
        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
		Wallet res= wService.addMoney(wallet.getWalletId(), amount);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/billPayment")
	public ResponseEntity<Wallet> payBill( Authentication auth,@RequestParam("BillAmount") Float billAmount){
        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
		Wallet res= wService.payRideBill(wallet.getWalletId(), billAmount);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/changeStatus")
	public ResponseEntity<Wallet> changeStatus(Authentication auth){
        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
		Wallet res= wService.changeStatus(wallet.getWalletId());
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/createWallet/{email}")
	public ResponseEntity<Wallet> createWallet( @PathVariable String email){
		Wallet res= wService.createWallet(email);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	@GetMapping("/getWallet")
	 public ResponseEntity<Wallet> getWallet(Authentication auth){
        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
		return new ResponseEntity<Wallet>(wallet, HttpStatus.ACCEPTED);
	 }
	
	@GetMapping("/WalletDetails")
	public ResponseEntity<Wallet> getLoggedUserWallet(Authentication auth){
		if(auth.getName()==null)
			  throw new WalletException("User is not logged into the system");
	    Wallet res= wService.getLoggedUserWallet(auth.getName());
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
}
