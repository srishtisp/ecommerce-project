package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppUsers;
import com.example.demo.repo.AppUsersRepo;

@Service
public class AppUsersService {

    @Autowired
    private AppUsersRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public AppUsers register(AppUsers user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public AppUsers findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }
}