package com.playtomic.tests.wallet.model.repository;

import com.playtomic.tests.wallet.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
}
