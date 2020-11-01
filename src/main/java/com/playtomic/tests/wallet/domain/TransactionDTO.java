package com.playtomic.tests.wallet.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDTO {

    private int id;

    private int userId;

    private int walletId;

    private TransactionType type;

    private BigDecimal amount;

    private boolean valid;

}
