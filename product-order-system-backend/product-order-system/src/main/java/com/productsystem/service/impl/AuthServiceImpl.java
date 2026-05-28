package com.productsystem.service.impl;

import com.productsystem.dto.LoginRequest;
import com.productsystem.dto.LoginResponse;
import com.productsystem.dto.RegisterRequest;
import com.productsystem.entity.Role;
import com.productsystem.entity.User;
import com.productsystem.repository.UserRepository;
import com.productsystem.security.JWTUtil;
import com.productsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists.");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.getEmail());
        loginResponse.setRole(user.getRole());
        loginResponse.setToken(token);
        return loginResponse;
    }
}
