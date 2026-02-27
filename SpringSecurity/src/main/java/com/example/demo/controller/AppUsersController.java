package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AppUsers;
import com.example.demo.security.JwtService;
import com.example.demo.service.AppUsersService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AppUsersController {

    @Autowired
    private AppUsersService service;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @PostMapping("/register")
    public AppUsers register(@RequestBody AppUsers user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUsers user) {

        AppUsers dbUser = service.findByUsername(user.getUsername());

        if (dbUser != null &&
            encoder.matches(user.getPassword(), dbUser.getPassword())) {

            return jwtService.generateToken(
                    dbUser.getUsername(),
                    dbUser.getRole().name());
        }

        throw new RuntimeException("Invalid credentials");
    }
}