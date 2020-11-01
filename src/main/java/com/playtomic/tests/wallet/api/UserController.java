package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.domain.NewUserDTO;
import com.playtomic.tests.wallet.domain.UserDTO;
import com.playtomic.tests.wallet.domain.request.NewUserBody;
import com.playtomic.tests.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody NewUserBody newUserBody) {

        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(newUserBody.getName());
        newUserDTO.setSurname(newUserBody.getSurname());
        newUserDTO.setBirthdate(newUserBody.getBirthdate());

        UserDTO userDTO = userService.createUserAndWallet(newUserDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}