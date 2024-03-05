package com.safar.controller;

import com.safar.entity.Users;
import com.safar.exceptions.UsersException;
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

//    http://localhost:8888/WALLET/addMoney?amount=900.0


    /**
     * Adds money to a user's wallet.
     *
     *
     * @param auth   The authentication object for the logged-in user
     * @param amount The amount of money to add.
     * @author Ankit choubey
     * @return The updated wallet.
     *
     */
	@PostMapping("/addMoney")
	public ResponseEntity<Wallet>addMoneyToWallet(Authentication auth, @RequestParam("amount") Float amount){
        if(auth==null)
              throw new UsersException("User not logged in");

        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
		Wallet res= wService.addMoney(wallet.getWalletId(), amount);
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
    /**
     * Changes the status of a wallet.
     *
     * @param auth The authentication object for the logged-in user.
     * @author Ankit choubey
     * @return The updated wallet.
     */
    @PatchMapping("/changeStatus")
    public ResponseEntity<Wallet> changeStatus(Authentication auth) {
        Users user = userService.getUserDetailsByEmail(auth.getName());
        Wallet wallet = user.getWallet();
        Wallet res = wService.changeStatus(wallet.getWalletId());
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    /**
     * Creates a wallet for a user.
     *
     * @param email The email of the user for whom to create the wallet.
     * @author Ankit choubey
     * @return The created wallet.
     */
    @PostMapping("/createWallet")
    public ResponseEntity<Wallet> createWallet(@RequestParam("email") String email) {
        Wallet res = wService.createWallet(email);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Retrieves a wallet details using its id.
     *
     * @param wid Wallet id
     * @author Ankit choubey
     * @return The wallet details
     */
    @GetMapping("/getWallet/{wid}")
    public ResponseEntity<Wallet> getWallet( @PathVariable("wid") Integer wid ) {

        Wallet wallet = wService.getWallet(wid);
        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
    }

    /**
     * Retrieves the wallet details of the logged-in user.
     *
     * @param auth The authentication object for the logged-in user.
     * @return The wallet details of the logged-in user.
     * @author Ankit choubey
     */
    @GetMapping("/WalletDetails")
    public ResponseEntity<Wallet> getLoggedUserWallet(Authentication auth) {
        if (auth.getName() == "")
            throw new WalletException("User is not logged into the system");
        Wallet res = wService.getLoggedUserWallet(auth.getName());
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
