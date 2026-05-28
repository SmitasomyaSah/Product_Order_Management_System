package com.productsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponse {

    private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private Double totalPrice;
}
