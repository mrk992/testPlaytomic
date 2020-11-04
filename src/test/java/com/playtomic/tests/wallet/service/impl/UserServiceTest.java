package com.playtomic.tests.wallet.service.impl;


import com.playtomic.tests.wallet.domain.NewUserDTO;
import com.playtomic.tests.wallet.domain.UserDTO;
import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.model.repository.IUserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private WalletService walletService;

    @MockBean
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void test_Create_User() {

        Date birthDate = new Date();

        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName("Test");
        newUserDTO.setSurname("ApellidoTest");
        newUserDTO.setBirthdate(birthDate);

        User user = new User();
        user.setName("Test");
        user.setSurname("ApellidoTest");
        user.setBirthdate(birthDate);

        User userCreated = new User();
        userCreated.setId(1);
        userCreated.setName("Test");
        userCreated.setSurname("ApellidoTest");
        userCreated.setBirthdate(birthDate);

        Wallet walletMock = new Wallet();
        walletMock.setId(1);

        Mockito.when(userRepository.save(user)).thenReturn(userCreated);
        Mockito.when(walletService.create(userCreated.getId())).thenReturn(walletMock);

        UserDTO result = userService.createUserAndWallet(newUserDTO);
        assertThat(result.getUserId(), is(userCreated.getId()));
    }
}
