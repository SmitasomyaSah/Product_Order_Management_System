package com.productsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {

    private String productName;

    private Double price;

    private Integer quantity;

    private Double totalPrice;
}