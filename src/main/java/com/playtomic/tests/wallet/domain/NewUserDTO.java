package com.playtomic.tests.wallet.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewUserDTO {
    private String name;
    private String surname;
    private Date birthdate;
}
