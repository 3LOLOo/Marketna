package com.example.E_Commerce_API.service;

import com.example.E_Commerce_API.exceptions.InsufficientBalance;
import com.example.E_Commerce_API.model.*;
import com.example.E_Commerce_API.repo.CartItemRepo;
import com.example.E_Commerce_API.repo.CartRepo;
import com.example.E_Commerce_API.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getUserOrders(User user) {
        return orderRepo.findByUser(user);
    }

    @Transactional
    public Object checkout(User user) {
        Cart currentCart = cartRepo.findByUser_Id(user.getId());
        List<CartItem> cartItems = currentCart.getCartItems();

        Order currentOrder = new Order();

        currentOrder.setUser(user);
        orderRepo.save(currentOrder);

        List<OrderItem> orderItems = cartItems.stream()
                                            .map(cartItem -> {
                                                OrderItem orderItem = new OrderItem();
                                                OrderItemId orderItemId = new OrderItemId(currentOrder.getId(), cartItem.getProduct().getId());

                                                orderItem.setOrderItemId(orderItemId);
                                                orderItem.setOrder(currentOrder);
                                                orderItem.setProduct(cartItem.getProduct());
                                                orderItem.setQuantity(cartItem.getQuantity());

                                                Double discountMoney = 0.0;

                                                LocalDateTime now = LocalDateTime.now();
                                                LocalDateTime start = cartItem.getProduct().getDiscountStart();
                                                LocalDateTime end = cartItem.getProduct().getDiscountEnd();

                                                if (cartItem.getProduct().getDiscount()!=0.0 && start != null && end != null && now.isAfter(start) && now.isBefore(end)) {
                                                    discountMoney = (cartItem.getProduct().getPrice() * cartItem.getProduct().getDiscount()) / 100;
                                                }
                                                orderItem.setPrice((cartItem.getProduct().getPrice() - discountMoney)  * cartItem.getQuantity());
                                                return orderItem;
                                            })
                                            .collect(Collectors.toList());

        double totalPrice = orderItems.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();

        currentOrder.setOrderItems(orderItems);
        currentOrder.setTotalPrice(totalPrice);

        if(user.getWallet().getBalance() >= totalPrice){
            user.getWallet().setBalance(user.getWallet().getBalance() - totalPrice);
        }
        else{
            throw new InsufficientBalance("Insufficient balance");
        }

        orderRepo.save(currentOrder);

        cartItemRepo.deleteAll(cartItems);

        return currentOrder;
    }

//    public Optional<Order> getOrder(int orderId) {
//        return orderRepo.findById(orderId);
//    }
}
