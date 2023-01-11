package com.rainbowforest.orderservice.service;

import com.rainbowforest.orderservice.domain.Order;

public interface OrderService {
    Order saveOrder(Order order);
}
