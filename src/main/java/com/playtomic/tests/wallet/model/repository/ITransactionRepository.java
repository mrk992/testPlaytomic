package com.playtomic.tests.wallet.model.repository;

import com.playtomic.tests.wallet.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findByWalletId(int walletId);
}
