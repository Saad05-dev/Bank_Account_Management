package com.fsa.BankAccountManagement.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsa.BankAccountManagement.model.Account;
import com.fsa.BankAccountManagement.model.Transaction;
import com.fsa.BankAccountManagement.repository.AccountRepository;
import com.fsa.BankAccountManagement.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService 
{
    @Autowired
    AccountRepository accRepo;

    @Autowired
    TransactionRepository trRepo;

    @Transactional
    public Transaction transfer(Account s,Account r,BigDecimal amount)
    {
        if(amount.compareTo(BigDecimal.ZERO) <= 0 )
            throw new IllegalArgumentException("Amout must be greater than 0");
        if(s.getBalance().compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient balance");
        s.setBalance(s.getBalance().subtract(amount));
        r.setBalance(r.getBalance().add(amount));
        s.setUpdatedAt(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());
        accRepo.save(s);
        accRepo.save(r);

        Transaction transaction = new Transaction();
        transaction.setAccount(s);
        transaction.setTargetAccount(r);
        transaction.setAmount(amount);
        transaction.setTypeOfTransaction("transfer");
        transaction.setTimeOfTransaction(LocalDateTime.now());

        return trRepo.save(transaction);
    }    

    @Transactional
    public Transaction deposit(Account account,BigDecimal amount)
    {
        if(amount.compareTo(BigDecimal.ZERO) <= 0 )
            throw new IllegalArgumentException("Amout must be greater than 0");
        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(LocalDateTime.now());
        accRepo.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTargetAccount(null);
        transaction.setAmount(amount);
        transaction.setTypeOfTransaction("deposit");
        transaction.setTimeOfTransaction(LocalDateTime.now());

        return trRepo.save(transaction);
    }

    public List<Transaction> getHistory(Account account)
    {
        List<Transaction> sender = trRepo.findByAccount(account);
        List<Transaction> receiver = trRepo.findByTargetAccount(account);

        List<Transaction> history = new ArrayList<>();
        history.addAll(sender);
        history.addAll(receiver);
        return history;
    }
}
