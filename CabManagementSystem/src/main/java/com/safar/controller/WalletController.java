package com.safar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.safar.entity.Wallet;
import com.safar.service.WalletServices;

@RestController
@RequestMapping("/WALLET")
public class WalletController {

    @Autowired
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
	
	@PatchMapping("/changeStatus/{Id}")
	public ResponseEntity<Wallet> changeStatus( @PathVariable("Id") Integer walletId){
		Wallet res= wService.changeStatus(walletId);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/createWallet/{email}")
	public ResponseEntity<Wallet> createWallet( @PathVariable String email){
		Wallet res= wService.createWallet(email);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	@GetMapping("/getWallet/{Id}")
	 public ResponseEntity<Wallet> getWallet(@PathVariable("Id") Integer id){
		Wallet res= wService.getWallet(id);
		return new ResponseEntity<Wallet>(res, HttpStatus.ACCEPTED);
	 }
}
