package com.safar.service;

import com.safar.entity.Transactions;
import com.safar.entity.Wallet;

import java.util.List;

public interface WalletServices {

    /**
     * Adds money to a wallet.
     *
     * @param walletId The ID of the wallet to add money to.
     * @param amount   The amount of money to add.
     * @return The updated wallet details.
     */
    public Wallet addMoney(Integer walletId, Float amount);

    /**
     * Retrieves all transactions associated with a wallet.
     *
     * @param walletId The ID of the wallet to retrieve transactions for.
     * @return A list of transactions associated with the wallet.
     */
    public List<Transactions> getAllTranactions(Integer walletId);

    /**
     * Pays a ride bill using a wallet.
     *
     * @param walletId The ID of the wallet to pay the bill from.
     * @param bill     The bill amount to be paid.
     * @return The updated wallet details.
     */
    public Wallet payRideBill(Integer walletId, Float bill);

    /**
     * Changes the status of a wallet (e.g., from Active to Inactive or vice versa).
     *
     * @param walletId The ID of the wallet to change the status of.
     * @return The updated wallet details.
     */
    public Wallet changeStatus(Integer walletId);

    /**
     * Creates a new wallet for a user with the given email.
     *
     * @param email The email of the user for whom to create a wallet.
     * @return The created wallet details.
     */
    public Wallet createWallet(String email);

    /**
     * Retrieves the details of a wallet by its ID.
     *
     * @param id The ID of the wallet to retrieve.
     * @return The wallet details.
     */
    public Wallet getWallet(Integer id);

    /**
     * Retrieves the wallet associated with a logged-in user by their email.
     *
     * @param email The email of the logged-in user.
     * @return The wallet details associated with the user.
     */
    public Wallet getLoggedUserWallet(String email);
}
