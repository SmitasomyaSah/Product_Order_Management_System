package com.productsystem.controller;

import com.productsystem.dto.AddToCartRequest;
import com.productsystem.dto.CartItemResponse;
import com.productsystem.dto.CartResponse;
import com.productsystem.dto.UpdateCartItemRequest;
import com.productsystem.entity.Cart;
import com.productsystem.entity.CartItem;
import com.productsystem.entity.Product;
import com.productsystem.entity.User;
import com.productsystem.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items")
    public CartResponse addToCart(@RequestParam Long userId, @Valid @RequestBody AddToCartRequest request) {
        return cartService.addToCart(userId,request);
    }

    @PutMapping("/items/{id}")
    public CartResponse updateCartItem(@PathVariable("id") Long cartItemId, @Valid @RequestBody UpdateCartItemRequest updateCartItemRequest) {
        return cartService.updateCartItem(cartItemId,updateCartItemRequest);
    }

    @DeleteMapping("/items/{id}")
    public CartResponse removeCartItem(@PathVariable("id") Long cartItemId) {
        return cartService.removeCartItem(cartItemId);
    }

    @GetMapping
    public CartResponse getCart(@RequestParam Long userId){
        return cartService.getCart(userId);
    }
}
