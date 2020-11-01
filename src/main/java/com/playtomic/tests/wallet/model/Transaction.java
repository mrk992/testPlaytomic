package com.playtomic.tests.wallet.model;

import com.playtomic.tests.wallet.domain.TransactionType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int userId;

    @Column
    private int walletId;

    @Column
    private TransactionType type;

    @Column
    private BigDecimal amount;

    @Column
    private boolean valid;
}
