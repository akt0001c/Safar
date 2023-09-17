package com.safar.service;

import com.safar.entity.Transactions;

import java.util.List;

public interface TransactionServices {

    /**
     * Retrieves all transactions associated with a wallet by its ID.
     *
     * @param walletId The ID of the wallet to retrieve transactions for.
     * @return A list of transactions associated with the wallet.
     */
    public List<Transactions> getAllTransationByWalledId(Integer walletId);
}
