package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.domain.BalanceDTO;
import com.playtomic.tests.wallet.model.Transaction;
import com.playtomic.tests.wallet.model.Wallet;

import java.math.BigDecimal;

public interface IWalletService {
    /**
     * Method to create a wallet
     * @param userId
     * @return the wallet created
     */
    Wallet create(int userId);

    /**
     * Method to do a recharge in a wallet
     * @param walletId to do the recharge
     * @param amount of the recharge
     * @return the transaction with a flag valid, which indicates if a transaction is valir or not
     */
    Transaction recharge(int walletId, BigDecimal amount);

    /**
     * Method to do a charge in a wallet
     * @param walletId to do the charge
     * @param amount of the charge
     * @return the transaction with a flag valid, which indicates if a transaction is valir or not
     */
    Transaction charge(int walletId, BigDecimal amount);

    /**
     * Method to calculate the balance of the wallet. Take all valid transaction for this walletid and calculate
     * the actual balance
     * @param walletId to calculate the balance
     * @return the balance of the wallet
     */
    BalanceDTO balanceWallet(int walletId);
}
