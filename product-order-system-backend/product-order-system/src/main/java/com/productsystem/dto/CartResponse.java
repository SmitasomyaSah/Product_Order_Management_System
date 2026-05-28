package com.productsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponse {

    private List<CartItemResponse> items;

    private Double totalAmount;
}
