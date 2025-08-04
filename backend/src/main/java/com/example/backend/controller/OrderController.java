package com.example.backend.controller;

import com.example.backend.kafka.KafkaOrderProducer;
import com.example.backend.model.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.request.OrderRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private KafkaOrderProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest orders){
        Order order = new Order();
        order.setUser(order.getUser());
        order.setItem(orders.getItem());
        order.setPrice(order.getPrice());
        order.setCreatedAt(LocalDateTime.now());

        Order saved = orderRepo.save(order);
        kafkaProducer.sendOrder(saved);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Order> list(){
        return orderRepo.findAll();
    }

}
