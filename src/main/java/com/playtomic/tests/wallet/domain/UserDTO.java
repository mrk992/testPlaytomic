package com.playtomic.tests.wallet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int walletId;
    private int userId;
    private String userName;
    private String surName;
}
