package com.productsystem.repository;

import com.productsystem.entity.Cart;
import com.productsystem.entity.Product;
import com.productsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUser(User user);
}
