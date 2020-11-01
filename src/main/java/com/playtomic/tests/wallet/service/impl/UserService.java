package com.playtomic.tests.wallet.service.impl;

import com.playtomic.tests.wallet.domain.NewUserDTO;
import com.playtomic.tests.wallet.domain.UserDTO;
import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.repository.IUserRepository;
import com.playtomic.tests.wallet.service.IUserService;
import com.playtomic.tests.wallet.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IWalletService walletService;

    @Override
    public UserDTO createUserAndWallet(NewUserDTO newUser) {

        User user = new User();
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setBirthdate(newUser.getBirthdate());

        User userCreated = userRepository.save(user);
        Wallet wallet = walletService.create(userCreated.getId());

        UserDTO userDTO = new UserDTO();
        userDTO.setWalletId(wallet.getId());
        userDTO.setUserId(userCreated.getId());
        userDTO.setUserName(userCreated.getName());
        userDTO.setSurName(userCreated.getSurname());

        return userDTO;
    }
}
