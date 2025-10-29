package com.felfel.springsecurity.service;

import com.felfel.springsecurity.model.User;
import com.felfel.springsecurity.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
