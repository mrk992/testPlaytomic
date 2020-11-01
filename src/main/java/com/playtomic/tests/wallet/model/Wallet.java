package com.playtomic.tests.wallet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int userId;
}
