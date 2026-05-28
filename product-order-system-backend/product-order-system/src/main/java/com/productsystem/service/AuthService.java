package com.productsystem.service;

import com.productsystem.dto.LoginRequest;
import com.productsystem.dto.LoginResponse;
import com.productsystem.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
