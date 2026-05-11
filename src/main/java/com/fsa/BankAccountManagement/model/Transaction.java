package com.fsa.BankAccountManagement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TRANSACTION",indexes = {
    @Index(name = "idx_account_id", columnList = "account_id"),
    @Index(name = "idx_target_account_id", columnList = "target_account_id")
})
@Data
public class Transaction 
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private BigDecimal amount;
    @Column(name = "type_of_transaction")
    private String typeOfTransaction;
    @Column(name = "time_of_transaction")
    private LocalDateTime timeOfTransaction;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "target_account_id", nullable = true)
    private Account targetAccount;

}
