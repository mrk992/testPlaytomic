package com.playtomic.tests.wallet.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ChargeBody {

    private int walletId;

    private BigDecimal amount;
}
