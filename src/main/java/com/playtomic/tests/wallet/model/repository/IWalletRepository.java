package com.playtomic.tests.wallet.model.repository;

import com.playtomic.tests.wallet.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends CrudRepository<Wallet, Integer> {
}
