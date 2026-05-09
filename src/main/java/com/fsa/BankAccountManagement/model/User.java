package com.fsa.BankAccountManagement.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User 
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private String email;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    private String password;
    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String phone;

    @OneToMany
    private List<Account> accounts;
}
