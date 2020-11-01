package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.domain.BalanceDTO;
import com.playtomic.tests.wallet.domain.TransactionType;
import com.playtomic.tests.wallet.model.Transaction;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.repository.ITransactionRepository;
import com.playtomic.tests.wallet.model.repository.IWalletRepository;
import com.playtomic.tests.wallet.service.IWalletService;
import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.service.PaymentServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WalletService implements IWalletService {

    @Autowired
    IWalletRepository walletRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public Wallet create(int userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);

        walletRepository.save(wallet);

        return wallet;
    }

    public Transaction recharge(int walletId, BigDecimal amount) {

        if (!walletRepository.existsById(walletId)) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.RECHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);
        try {
            paymentService.charge(amount);
            transaction.setValid(true);
        } catch (PaymentServiceException exception) {
            transaction.setValid(false);
        }

        return transactionRepository.save(transaction);
    }

    public Transaction charge(int walletId, BigDecimal amount) {

        if (!walletRepository.existsById(walletId)) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);
        transaction.setValid(balanceWallet(walletId).getAmount().compareTo(amount) >= 0);

        return transactionRepository.save(transaction);
    }

    public BalanceDTO balanceWallet(int walletId){

        if (!walletRepository.existsById(walletId)) {
            return null;
        }
        List<Transaction> transactionsValid = transactionRepository.findByWalletId(walletId).
                stream().filter(Transaction::isValid).collect(Collectors.toList());

        BigDecimal amountCharges = transactionsValid.stream().
                filter(transaction -> transaction.getType() == TransactionType.CHARGE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal amountRecharges = transactionsValid.stream().
                filter(transaction -> transaction.getType() == TransactionType.RECHARGE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setWalletId(walletId);
        balanceDTO.setAmount(amountRecharges.subtract(amountCharges));

        return balanceDTO;
    }
}
