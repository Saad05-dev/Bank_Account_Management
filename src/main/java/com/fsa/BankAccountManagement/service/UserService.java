package com.fsa.BankAccountManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsa.BankAccountManagement.model.User;
import com.fsa.BankAccountManagement.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEnc;

    public User register(User user)
    {
        Optional<User> userEmail = userRepo.findByEmail(user.getEmail());
        if(userEmail.isPresent())
            throw new RuntimeException("Email already exists");
        user.setPassword(passwordEnc.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User login(String email, String password)
    {
        Optional<User> found = userRepo.findByEmail(email);
        if(found.isEmpty())
            throw new RuntimeException("User not found"); 
        User user = found.get();
        if(!passwordEnc.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid password");
        return user;
    }
}
