package com.productsystem.service.impl;

import com.productsystem.dto.OrderItemResponse;
import com.productsystem.dto.OrderResponse;
import com.productsystem.entity.*;
import com.productsystem.repository.*;
import com.productsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with userid: "+userId));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("Cart not found "));
        List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
        if(cartItemList.isEmpty()){
            throw new RuntimeException("Cart is empty");
        }
        double totalAmount=0;
        for(CartItem item:cartItemList){
            Product product =item.getProduct();
            if(product.getQuantity()< item.getQuantity()){
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            totalAmount += item.getPrice()* item.getQuantity();
        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order=orderRepository.save(order);
        List<OrderItem> orderItemList = new ArrayList<>();
        for(CartItem item:cartItemList){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItemList.add(orderItem);
        }
        orderItemRepository.saveAll(orderItemList);
        for(OrderItem item:orderItemList){
            Product product=item.getProduct();
            product.setQuantity(product.getQuantity()- item.getQuantity());
        }
        cartItemRepository.deleteAll(cartItemList);
        return getOrderById(order.getId());
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order:orders){
            orderResponses.add(getOrderById(order.getId()));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found with id: "+id));
        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);
        List<OrderItemResponse> orderItemResponses=new ArrayList<>();
        for(OrderItem orderItem:orderItemList){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductName(orderItem.getProduct().getName());
            orderItemResponse.setPrice(orderItem.getPrice());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            double itemTotal=orderItem.getPrice()*orderItem.getQuantity();
            orderItemResponse.setTotalPrice(itemTotal);
            orderItemResponses.add(orderItemResponse);
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setStatus(order.getOrderStatus());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setItems(orderItemResponses);
        return orderResponse;
    }
}
