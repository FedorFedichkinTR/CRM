package com.time.to.shop.crm.service;

import com.time.to.shop.crm.model.db.Customer;
import com.time.to.shop.crm.model.db.Order;
import com.time.to.shop.crm.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        if (Objects.isNull(order.getSaleDate())) {
            order.setSaleDate(LocalDateTime.now());
        }
        return orderRepository.save(order);
    }

    public Order saveOrderWithCustomer(Order order, Customer customer) {
        order.setCustomer(customerService.save(customer));
        return save(order);
    }
}
