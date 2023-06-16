package com.safar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safar.entity.Wallet;
import com.safar.service.WalletServices;

@RestController
@RequestMapping("/WALLET")
public class WalletController {
	
	private WalletServices wService;
    
	@PostMapping("/addMoney")
	public ResponseEntity<Wallet>  addMoneyToWallet(@RequestParam("Id") Integer walletId , @RequestParam("amount") Float amount){
     
		Wallet res= wService.addMoney(walletId, amount);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/billPayment")
	public ResponseEntity<Wallet> payBill( @RequestParam("Id") Integer walletId,@RequestParam("BillAmount") Float billAmount){
		Wallet res= wService.payRideBill(walletId, billAmount);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/changeStatus/{Id}")
	public ResponseEntity<Wallet> changeStatus( @PathVariable("Id") Integer walletId){
		Wallet res= wService.changeStatus(walletId);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
}