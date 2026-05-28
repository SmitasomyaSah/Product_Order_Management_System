package com.productsystem.service;

import com.productsystem.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(Long userId);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);
}
