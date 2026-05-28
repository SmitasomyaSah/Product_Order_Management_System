package com.productsystem.dto;

import com.productsystem.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long orderId;

    private OrderStatus status;

    private List<OrderItemResponse> items;

    private Double totalAmount;
}
