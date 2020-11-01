package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.domain.NewUserDTO;
import com.playtomic.tests.wallet.domain.UserDTO;

public interface IUserService {

    /**
     * Method to create a user and a wallet associated
     *
     * @param newUser
     * @return a user created
     */
    UserDTO createUserAndWallet(NewUserDTO newUser);
}
