package com.playtomic.tests.wallet.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BalanceDTO {


    private int walletId;

    @NotNull
    @NotBlank
    private BigDecimal amount;
}
