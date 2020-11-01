package com.playtomic.tests.wallet.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
