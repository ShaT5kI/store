package com.example.store.service;

import com.example.store.entity.Order;
import com.example.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addNew(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Orders not found with id: " + id));
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

}
