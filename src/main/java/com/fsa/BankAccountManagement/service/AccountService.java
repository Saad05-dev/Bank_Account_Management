package com.fsa.BankAccountManagement.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsa.BankAccountManagement.model.Account;
import com.fsa.BankAccountManagement.model.User;
import com.fsa.BankAccountManagement.repository.AccountRepository;

@Service
public class AccountService 
{
    @Autowired
    private AccountRepository accRepo;


    public Account createAccount(User user)
    {
        Account ac = new Account();
        ac.setUser(user);
        ac.setBalance(BigDecimal.ZERO);
        ac.setAccountNumber(UUID.randomUUID().toString());
        ac.setStatus(true);
        ac.setCreatedAt(LocalDateTime.now());
        ac.setUpdatedAt(LocalDateTime.now());
        return accRepo.save(ac);
    }
    public List<Account> getAccountByUser(User user)
    {
        return accRepo.findByUser(user);
    }
}
