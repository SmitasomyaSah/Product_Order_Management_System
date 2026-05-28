package com.productsystem.service;

import com.productsystem.dto.AddToCartRequest;
import com.productsystem.dto.CartResponse;
import com.productsystem.dto.UpdateCartItemRequest;

public interface CartService {

    CartResponse addToCart(Long userId,AddToCartRequest request);

    CartResponse updateCartItem(Long cartItemId,UpdateCartItemRequest updateCartItemRequest);

    CartResponse removeCartItem(Long cartItemId );

    CartResponse getCart(Long userId);
}
