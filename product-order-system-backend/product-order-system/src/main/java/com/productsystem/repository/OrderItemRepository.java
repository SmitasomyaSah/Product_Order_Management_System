package com.productsystem.repository;

import com.productsystem.entity.Order;
import com.productsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrder(Order order);
}
