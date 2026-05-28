package com.productsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Product name should not be empty.")
    private String name;

    @NotNull(message = "Price should not be empty.")
    @Min(value = 1,message = "Price should be greater than 0.")
    private Double price;

    @NotNull(message = "Quantity should not be empty.")
    @Min(value = 0,message = "Quantity should not be negative.")
    private Integer quantity;
}
