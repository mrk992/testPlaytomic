package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.domain.BalanceDTO;
import com.playtomic.tests.wallet.domain.TransactionType;
import com.playtomic.tests.wallet.model.Transaction;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.repository.ITransactionRepository;
import com.playtomic.tests.wallet.model.repository.IWalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class WalletServiceTest {

    @MockBean
    IWalletRepository walletRepository;

    @MockBean
    ThirdPartyPaymentService paymentService;

    @MockBean
    ITransactionRepository transactionRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    public void test_Create_Wallet_Ok() {

        int userId = 1;
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);

        Wallet walletCreated = new Wallet();
        walletCreated.setId(1);
        walletCreated.setUserId(userId);

        Mockito.when(walletRepository.save(wallet)).thenReturn(walletCreated);

        Wallet result = walletService.create(userId);

        assertThat(wallet, is(result));
    }

    @Test
    public void test_Recharge_Wallet_Not_Ok() {

        BigDecimal amount = new BigDecimal(4);
        int walletId = 1;

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.RECHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);
        transaction.setValid(true);

        Transaction transactionSaved = new Transaction();
        transactionSaved.setType(TransactionType.RECHARGE);
        transactionSaved.setAmount(amount);
        transactionSaved.setWalletId(walletId);
        transactionSaved.setValid(true);

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionSaved);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        Transaction result = walletService.recharge(walletId, amount);

        assertThat(transactionSaved, is(result));
    }

    @Test
    public void test_Recharge_Wallet_Ok() {

        BigDecimal amount = new BigDecimal(4);
        int walletId = 1;
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.RECHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);
        transaction.setValid(true);

        Transaction transactionSaved = new Transaction();
        transactionSaved.setType(TransactionType.RECHARGE);
        transactionSaved.setAmount(amount);
        transactionSaved.setWalletId(walletId);
        transactionSaved.setValid(true);

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionSaved);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        Transaction result = walletService.recharge(walletId, amount);

        assertThat(transactionSaved, is(result));
    }

    @Test
    public void test_Charge_Wallet_Not_Valid() {

        BigDecimal amount = new BigDecimal(20);
        int walletId = 1;

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);
        transaction.setValid(false);


        Transaction transactionSaved = new Transaction();
        transactionSaved.setType(TransactionType.CHARGE);
        transactionSaved.setAmount(amount);
        transactionSaved.setWalletId(walletId);
        transactionSaved.setValid(false);

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionSaved);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        Transaction result = walletService.charge(walletId, amount);

        assertThat(transaction, is(result));
    }

    @Test
    public void test_Charge_Wallet_Valid() {

        BigDecimal amount = new BigDecimal(20);
        int walletId = 1;

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CHARGE);
        transaction.setAmount(amount);
        transaction.setWalletId(walletId);

        Transaction transactionSaved = new Transaction();
        transactionSaved.setType(TransactionType.CHARGE);
        transactionSaved.setAmount(amount);
        transactionSaved.setWalletId(walletId);
        transactionSaved.setValid(true);

        Mockito.when(transactionRepository.save(transaction)).thenReturn(transactionSaved);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        Transaction result = walletService.charge(walletId, amount);

        assertThat(transactionSaved, is(result));
    }

    @Test
    public void balanceWallet_Works_Good_Transactions() {

        List<Transaction> transactionsValid = new ArrayList<>();
        int walletId = 1;
        BigDecimal balance = new BigDecimal(14);

        Transaction transaction1 = new Transaction();
        transaction1.setType(TransactionType.CHARGE);
        transaction1.setAmount(new BigDecimal(2));
        transaction1.setWalletId(walletId);
        transaction1.setValid(true);

        Transaction transaction2 = new Transaction();
        transaction2.setType(TransactionType.CHARGE);
        transaction2.setAmount(new BigDecimal(4));
        transaction2.setWalletId(walletId);
        transaction2.setValid(true);

        Transaction transaction3 = new Transaction();
        transaction3.setType(TransactionType.RECHARGE);
        transaction3.setAmount(new BigDecimal(20));
        transaction3.setWalletId(walletId);
        transaction3.setValid(true);

        transactionsValid.add(transaction1);
        transactionsValid.add(transaction2);
        transactionsValid.add(transaction3);

        Mockito.when(transactionRepository.findByWalletId(walletId)).thenReturn(transactionsValid);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        BalanceDTO result = walletService.balanceWallet(walletId);

        assertThat(result.getAmount(), is(balance));

    }

    @Test
    public void balanceWallet_Works_Bad_Transactions() {

        List<Transaction> transactionsValid = new ArrayList<>();
        int walletId = 1;
        BigDecimal balance = new BigDecimal(-40);

        Transaction transaction1 = new Transaction();
        transaction1.setType(TransactionType.CHARGE);
        transaction1.setAmount(new BigDecimal(20));
        transaction1.setWalletId(walletId);
        transaction1.setValid(true);

        Transaction transaction2 = new Transaction();
        transaction2.setType(TransactionType.CHARGE);
        transaction2.setAmount(new BigDecimal(20));
        transaction2.setWalletId(walletId);
        transaction2.setValid(true);


        transactionsValid.add(transaction1);
        transactionsValid.add(transaction2);


        Mockito.when(transactionRepository.findByWalletId(walletId)).thenReturn(transactionsValid);
        Mockito.when(walletRepository.existsById(walletId)).thenReturn(true);

        BalanceDTO result = walletService.balanceWallet(walletId);

        assertThat(result.getAmount(), is(balance));

    }
}

