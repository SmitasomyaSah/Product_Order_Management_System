package com.productsystem.service.impl;

import com.productsystem.dto.AddToCartRequest;
import com.productsystem.dto.CartItemResponse;
import com.productsystem.dto.CartResponse;
import com.productsystem.dto.UpdateCartItemRequest;
import com.productsystem.entity.Cart;
import com.productsystem.entity.CartItem;
import com.productsystem.entity.Product;
import com.productsystem.entity.User;
import com.productsystem.repository.CartItemRepository;
import com.productsystem.repository.CartRepository;
import com.productsystem.repository.ProductRepository;
import com.productsystem.repository.UserRepository;
import com.productsystem.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartResponse addToCart(Long userId, AddToCartRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(()->new RuntimeException("Product not found "));
        if(product.getQuantity()< request.getQuantity()){throw new RuntimeException("Insufficient Quantity");}
        Cart cart = cartRepository.findByUser(user).orElseGet(()->createCart(user));
        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart,product).orElse(null);
        if(cartItem!=null){
            cartItem.setQuantity(cartItem.getQuantity()+ request.getQuantity());
        }
        else{
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(request.getQuantity());
        }
        cartItemRepository.save(cartItem);
        return getCart(userId);
    }

    @Override
    public CartResponse updateCartItem(Long cartItemId, UpdateCartItemRequest updateCartItemRequest) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("Cart Item not found with the cartItemId: "+cartItemId));
        cartItem.setQuantity(updateCartItemRequest.getQuantity());
        cartItemRepository.save(cartItem);
        Long userId = cartItem.getCart().getUser().getId();
        return getCart(userId);
    }

    @Override
    public CartResponse removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("Cart Item not found with the cartItemId: "+cartItemId));
        cartItemRepository.delete(cartItem);
        Long userId = cartItem.getCart().getUser().getId();
        return getCart(userId);
    }

    @Override
    public CartResponse getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: "+userId));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("Cart not found"));
        List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        double totalAmount=0;
        for(CartItem c:cartItemList){
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setProductId(c.getProduct().getId());
            cartItemResponse.setProductName(c.getProduct().getName());
            cartItemResponse.setPrice(c.getPrice());
            cartItemResponse.setQuantity(c.getQuantity());
            double itemTotal=c.getPrice()*c.getQuantity();
            cartItemResponse.setTotalPrice(itemTotal);
            totalAmount+=itemTotal;

            cartItemResponseList.add(cartItemResponse);
        }
        CartResponse cartResponse = new CartResponse();
        cartResponse.setItems(cartItemResponseList);
        cartResponse.setTotalAmount(totalAmount);
        return cartResponse;
    }

    private Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
