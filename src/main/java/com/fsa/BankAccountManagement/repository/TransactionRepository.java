package com.fsa.BankAccountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsa.BankAccountManagement.model.Account;
import com.fsa.BankAccountManagement.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    public List<Transaction> findByAccount(Account account);
    public List<Transaction> findByTargetAccount(Account targetAccount);
}
