package com.productsystem.dto;

import com.productsystem.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String address;

    @NotBlank
    @Size(min=5,message = "Password must be at least 5 characters")
    private String password;

}
