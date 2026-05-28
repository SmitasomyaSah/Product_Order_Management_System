package com.productsystem.controller;

import com.productsystem.dto.LoginRequest;
import com.productsystem.dto.LoginResponse;
import com.productsystem.dto.RegisterRequest;
import com.productsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

   @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
       return authService.login(request);
    }
}
