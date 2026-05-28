package com.productsystem.repository;

import com.productsystem.entity.Order;
import com.productsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
