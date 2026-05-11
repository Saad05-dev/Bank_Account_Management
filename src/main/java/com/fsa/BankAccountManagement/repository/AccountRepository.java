package com.fsa.BankAccountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsa.BankAccountManagement.model.Account;
import com.fsa.BankAccountManagement.model.User;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    List<Account> findByUser(User user);
}
